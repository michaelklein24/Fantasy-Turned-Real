import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule, NgForm } from '@angular/forms';
import { AxiosResponse } from 'axios';
import { SessionService } from '../../services/session.service';
import { Router, RouterModule } from '@angular/router';
import { LoginUserResponse } from '../../shared/generated';
import { ToastService } from '../../services/toast.service';

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

  async onSubmit(form: NgForm) {
    const email = form.value.email;
    const password = form.value.password;

    try {
      const response: AxiosResponse<LoginUserResponse> =
        await this.authService.loginUser(email, password);
      const token: string = response.data.accessToken!;
      this.sessionService.startSession(token);
      this.router.navigate(['/dashboard']);
    } catch (error: any) {
      console.log(error);
      this.toastService.toastAxiosError('register account', error, 5000);
    }
  }
}
