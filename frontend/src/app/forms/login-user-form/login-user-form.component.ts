import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ToastService } from '../../core/services/toast.service';
import { AuthService } from '../../features/auth/services/auth.service';

@Component({
  selector: 'app-login-user-form',
  standalone: true,
  imports: [
    FormsModule, 
    RouterModule
  ],
  templateUrl: './login-user-form.component.html',
  styleUrls: ['./login-user-form.component.css'],
})
export class LoginUserFormComponent {
  constructor(
    private authService: AuthService,
    private toastService: ToastService,
    private router: Router
  ) {}

  private isFormInvalid(form: NgForm): boolean {
    if (form.invalid) {
      this.toastService.toastError('Please fill in all required fields.', 3000);
      return true;
    }
    return false;
  }

  onSubmit(form: NgForm): void {
    if (this.isFormInvalid(form)) return;

    const { email, password } = form.value;

    // Subscribe to the observable returned by the AuthService
    this.authService.loginUser(email, password).subscribe({
      next: () => {
        this.router.navigate(['/dashboard']);
        this.toastService.toastSuccess('Login successful!', 3000);
      },
      error: (error) => {
        this.toastService.toastApiError('log in', error, 5000);
      },
    });
  }
}
