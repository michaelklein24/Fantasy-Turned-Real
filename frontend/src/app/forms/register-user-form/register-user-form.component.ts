import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AxiosResponse } from 'axios';
import { RegisterUserResponse } from '../../../libs/generated';
import { ToastService } from '../../core/services/toast.service';
import { AuthService } from '../../features/auth/services/auth.service';
import { SessionService } from '../../features/auth/services/session.service';

@Component({
  selector: 'app-register-user-form',
  standalone: true,
  imports: [FormsModule, RouterModule],
  templateUrl: './register-user-form.component.html',
  styleUrl: './register-user-form.component.css',
})
export class RegisterUserFormComponent implements OnInit, OnDestroy {
  constructor(
    private authService: AuthService,
    private toastService: ToastService,
    private sessionService: SessionService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  ngOnDestroy(): void {}

  async onSubmit(form: NgForm): Promise<void> {
    if (form.invalid) {
      this.toastService.toastError('Please fill in all the required fields', 3000);
      return;
    }

    const { firstName, lastName, email, password } = form.value;

    try {
      await this.authService.registerUser(firstName, lastName, email, password);
      this.router.navigate(['/dashboard']);
    } catch (error: any) {
      console.error('Registration failed:', error);
      this.toastService.toastAxiosError('register account', error, 5000);
    }
  }
  
}
