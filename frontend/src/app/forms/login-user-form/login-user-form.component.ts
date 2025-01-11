import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AxiosResponse } from 'axios';
import { LoginUserResponse } from '../../../libs/generated';
import { ToastService } from '../../core/services/toast.service';
import { AuthService } from '../../features/auth/services/auth.service';
import { SessionService } from '../../features/auth/services/session.service';

@Component({
  selector: 'app-login-user-form',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './login-user-form.component.html',
  styleUrl: './login-user-form.component.css',
})
export class LoginUserFormComponent {
  constructor(
    private authService: AuthService,
    private toastService: ToastService,
    private sessionService: SessionService,
    private router: Router
  ) {}

  private isFormInvalid(form: NgForm): boolean {
    if (form.invalid) {
      this.toastService.toastError('Please fill in all required fields.', 3000);
      return true;
    }
    return false;
  }

  async onSubmit(form: NgForm): Promise<void> {
    if (this.isFormInvalid(form)) return;
  
    try {
      await this.authService.loginUser(form.value.email, form.value.password);
      this.router.navigate(['/dashboard']);
      this.toastService.toastSuccess('Login successful!', 3000);
    } catch (error) {
      console.error('Login failed:', error);
      this.toastService.toastAxiosError('log in', error, 5000);
    }
  }
}
