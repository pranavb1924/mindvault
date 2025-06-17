import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
  form: FormGroup;
  loading = false;

  // Feature showcase properties
  currentSlide = 0;
  slides = [
    { alt: 'Writing in journal' },
    { alt: 'Beautiful typography' },
    { alt: 'Secure and private' },
    { alt: 'Organized memories' }
  ];
  
  private slideInterval: any;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private auth: AuthService
  ) {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit() {
    // Start automatic slide progression
    this.startSlideShow();
  }

  ngOnDestroy() {
    // Clean up interval when component is destroyed
    if (this.slideInterval) {
      clearInterval(this.slideInterval);
    }
  }

  // Start automatic slide progression every 4 seconds
  startSlideShow() {
    this.slideInterval = setInterval(() => {
      this.nextSlide();
    }, 4000);
  }

  // Stop automatic slides (when user interacts)
  stopSlideShow() {
    if (this.slideInterval) {
      clearInterval(this.slideInterval);
    }
  }

  // Go to next slide
  nextSlide() {
    this.currentSlide = (this.currentSlide + 1) % this.slides.length;
  }

  // Go to specific slide (when user clicks indicator)
  goToSlide(index: number) {
    this.currentSlide = index;
    // Restart slideshow after user interaction
    this.stopSlideShow();
    setTimeout(() => {
      this.startSlideShow();
    }, 4000); // Wait 4 seconds before restarting auto-progression
  }

  // Existing methods
  err(ctrl: string) {
    const c = this.form.get(ctrl);
    return c && c.invalid && (c.touched || c.dirty);
  }

  submit() {
    if (this.form.invalid) { this.form.markAllAsTouched(); return; }
  
    this.loading = true;
    this.auth.login(this.form.value)
      .pipe(finalize(() => this.loading = false))
      .subscribe({
        next: (profile) => {
          /* 1️⃣ persist user for the dashboard guard */
          localStorage.setItem('currentUser', JSON.stringify(profile));
  
          /* 2️⃣ go to dashboard (we can pass state if you like) */
          this.router.navigate(['/dashboard'], {
            state: {
              id:        profile.id,
              firstName: profile.firstName,
              lastName:  profile.lastName
            }
          });
        },
        error: err => alert('Login failed: ' + (err.error || err.message))
      });
  }
  
}