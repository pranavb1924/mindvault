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
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit, OnDestroy {
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
      firstName: ['', Validators.required],
      lastName:  ['', Validators.required],
      email:     ['', [Validators.required, Validators.email]],
      password:  ['', [Validators.required, Validators.minLength(8)]]
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
    if (this.form.invalid) { 
      this.form.markAllAsTouched(); 
      return; 
    }

    this.loading = true;
    this.auth.register(this.form.value)
      .pipe(finalize(() => this.loading = false))
      .subscribe({
        next: () => {
          alert('Account created! Now sign in.');
          this.router.navigate(['/login']);
        },
        error: err => alert('Registration failed: ' +
          (err.error || err.message))
      });
  }
}