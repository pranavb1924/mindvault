// src/app/pages/dashboard/dashboard.component.ts
import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { JournalEntry } from '../../models/journal-entry.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule,FormsModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  /* ───────── dependencies ───────── */
  private auth  = inject(AuthService);
  private fb    = inject(FormBuilder);
  private router = inject(Router);

  /* ───────── state ───────── */
  userId   = 0;
  fullName = '';
  tab: 'new' | 'list' = 'list';
  today = new Date();

  form!: FormGroup;
  entries: JournalEntry[] = [];
  selectedEntry: JournalEntry | null = null;   // for edit
  viewingEntry:  JournalEntry | null = null;   // for read-only modal
  selectedFont: string = 'Edu SA Hand'

  constructor() {
    /* route guard */
    const stored = localStorage.getItem('currentUser');
    if (!stored) {
      console.log("Hello The problem lies Here")
      this.router.navigate(['/register']);
      return;
    }
    const u = JSON.parse(stored) as { id: number; firstName: string; lastName: string };
    this.userId   = u.id;
    this.fullName = `${u.firstName} ${u.lastName}`.trim();

    /* build form */
    this.form = this.fb.group({
      title:   ['', Validators.required],
      content: ['', Validators.required]
    });

    this.loadEntries();
  }

  /* ───────── UI helpers ───────── */
  switch(t: 'new' | 'list') {
    this.tab = t;
    this.selectedEntry = null;
    this.form.reset();
  }




  formatDate(dateStr?: string): string {
    if (!dateStr) return '';
    const d = new Date(dateStr);
    const day = d.getDate();
    const month = d.toLocaleString('default', { month: 'long' });
    const year = d.getFullYear();
    const suffix = (n: number) => (n>3&&n<21)?'th':['st','nd','rd'][((n%10)+9)%10-9]||'th';
    return `${day}${suffix(day)} ${month}, ${year}`;
  }

  /* ───────── CRUD ───────── */
  save(): void {
    if (this.form.invalid) return;
    const body = this.form.value as Pick<JournalEntry,'title'|'content'>;

    if (this.selectedEntry?.id != null) {
      // update existing
      this.auth.updateEntry(this.userId, this.selectedEntry.id, body)
        .subscribe({
          next: upd => {
            const i = this.entries.findIndex(e=>e.id===upd.id);
            if (i>-1) this.entries[i]= upd;
            this.switch('list');
          },
          error: err => console.error('Update failed', err)
        });
    } else {
      // create new
      this.auth.createEntry(this.userId, body)
        .subscribe({
          next: e => { this.entries.unshift(e); this.switch('list'); },
          error: err => console.error('Create failed', err)
        });
    }
  }

  editEntry(entry: JournalEntry): void {
    this.selectedEntry = { ...entry };
    this.viewingEntry  = null;
    this.tab = 'new';
    this.form.patchValue({ title: entry.title, content: entry.content });
  }

  viewEntry(entry: JournalEntry): void {
    this.viewingEntry  = entry;
  }

  closeView(): void {
    this.viewingEntry = null;
  }

  deleteEntry(entryId?: number): void {
    if (entryId == null) return;
    if (!confirm('Delete this entry?')) return;
    this.auth.deleteEntry(this.userId, entryId)
      .subscribe({
        next: () => {
          this.entries = this.entries.filter(e=>e.id!==entryId);
          this.switch('list');
        },
        error: err => console.error('Delete failed', err)
      });
  }

  loadEntries(): void {
    this.auth.getEntries(this.userId)
      .subscribe({
        next: list => this.entries = list,
        error: err  => console.error('Load failed', err)
      });
  }

  // Add these properties to your component class:

// Font controls for diary modal
fontControls = {
  family: 'Kalam',
  size: 18,           // Font size for content
  titleSize: 32,      // Font size for title
  weight: '400',      // Font weight
  lineHeight: '1.6',  // Line height
  color: '#34495e',   // Text color
  letterSpacing: 0    // Letter spacing in pixels
};

// Method to adjust font size with buttons
adjustFontSize(change: number): void {
  this.fontControls.size = Math.max(12, Math.min(32, this.fontControls.size + change));
  this.fontControls.titleSize = Math.max(20, Math.min(48, this.fontControls.titleSize + change * 2));
}

// Method to reset font controls to defaults
resetFontControls(): void {
  this.fontControls = {
    family: 'Kalam',
    size: 18,
    titleSize: 32,
    weight: '400',
    lineHeight: '1.6',
    color: '#34495e',
    letterSpacing: 0
  };
}
}




