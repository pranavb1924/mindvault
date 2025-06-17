import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import type { JournalEntry } from '../models/journal-entry.model';

// DTOs and response types
export interface RegisterDto {
  firstName: string;
  lastName:  string;
  email:     string;
  password:  string;
}

export interface LoginDto {
  email:    string;
  password: string;
}

export interface UserProfile {
  id:        number;
  firstName: string;
  lastName:  string;
  email:     string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private api  = 'http://localhost:8080/api';

  register(dto: RegisterDto): Observable<any> {
    return this.http.post<any>(`${this.api}/users`, dto);
  }

  login(dto: LoginDto): Observable<UserProfile> {
    return this.http.post<UserProfile>(`${this.api}/auth/login`, dto);
  }

  /* ───────── Journal helpers ───────── */
  createEntry(userId: number, body: Pick<JournalEntry, 'title' | 'content'>): Observable<JournalEntry> {
    return this.http.post<JournalEntry>(
      `${this.api}/entries/${userId}`,
      body
    );
  }

  getEntries(userId: number): Observable<JournalEntry[]> {
    return this.http.get<JournalEntry[]>(`${this.api}/entries/${userId}`);
  }

  updateEntry(userId: number, entryId: number, body: Pick<JournalEntry, 'title' | 'content'>): Observable<JournalEntry> {
    return this.http.put<JournalEntry>(
      `${this.api}/entries/${userId}/${entryId}`,
      body
    );
  }

  deleteEntry(userId: number, entryId: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/entries/${userId}/${entryId}`);
  }
  

  
}
