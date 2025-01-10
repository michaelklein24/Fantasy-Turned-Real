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

  async onSubmit(form: NgForm) {
    const firstName = form.value.firstName;
    const lastName = form.value.lastName;
    const email = form.value.email;
    const password = form.value.password;
  
    try {
      const response: AxiosResponse<RegisterUserResponse > = 
        await this.authService.registerUser(firstName, lastName, email, password);    
        console.log(response)  
      const toastMessage = `Welcome ${firstName}`;
      const token : string = response.data.accessToken!;
      this.sessionService.startSession(token);
      this.toastService.toastSuccess(toastMessage, 3000);
      this.router.navigate(["/dashboard"]);
    } catch (error: any) {
      console.log(error)
      this.toastService.toastAxiosError("register account", error, 5000);
    }
  }  
  
}
