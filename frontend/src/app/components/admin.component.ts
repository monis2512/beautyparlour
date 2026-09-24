import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ApiService, Query } from '../services/api.service';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './admin.component.html'
})
export class AdminComponent {
  api = inject(ApiService);
  fb = inject(FormBuilder);
  logged = false;
  loading = true;
  error = '';
  queries: Query[] = [];
  private poll?: ReturnType<typeof setInterval>;

  loginForm = this.fb.nonNullable.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  ngOnInit() {
    this.api.session().subscribe({
      next: r => {
        this.logged = r.loggedIn;
        this.loading = false;
        if (this.logged) this.startDashboard();
      },
      error: () => this.loading = false
    });
  }

  ngOnDestroy() {
    if (this.poll) clearInterval(this.poll);
  }

  login() {
    this.error = '';
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }
    const v = this.loginForm.getRawValue();
    this.api.login(v.username, v.password).subscribe({
      next: () => {
        this.logged = true;
        this.startDashboard();
      },
      error: () => this.error = 'Invalid username or password.'
    });
  }

  startDashboard() {
    this.load();
    if (this.poll) clearInterval(this.poll);
    this.poll = setInterval(() => this.load(), 10000);
  }

  load() {
    this.api.queries().subscribe({
      next: q => this.queries = q,
      error: e => {
        if (e.status === 401) {
          this.logged = false;
          if (this.poll) clearInterval(this.poll);
        }
      }
    });
  }

  delete(id: number) {
    if (confirm('Delete this enquiry?')) {
      this.api.delete(id).subscribe(() => this.load());
    }
  }

  logout() {
    this.api.logout().subscribe(() => {
      this.logged = false;
      this.queries = [];
      if (this.poll) clearInterval(this.poll);
      window.location.href = '/';
    });
  }

  call(mobile: string) {
    window.location.href = 'tel:' + mobile;
  }

  formatDate(value?: string) {
    if (!value) return '';
    const d = new Date(value);
    return new Intl.DateTimeFormat('en-IN', {
      timeZone: 'Asia/Kolkata', day: '2-digit', month: 'short', year: 'numeric',
      hour: '2-digit', minute: '2-digit', hour12: true
    }).format(d);
  }
}
