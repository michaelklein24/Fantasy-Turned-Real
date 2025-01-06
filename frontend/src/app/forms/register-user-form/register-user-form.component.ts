import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule, NgForm } from '@angular/forms';
import { ToastService } from '../../services/toast.service';
import {
  RegisterUserResponse,
} from '../../shared/ApiClient';
import { AxiosResponse } from 'axios';

@Component({
  selector: 'app-register-user-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register-user-form.component.html',
  styleUrl: './register-user-form.component.css',
})
export class RegisterUserFormComponent implements OnInit, OnDestroy {
  constructor(
    private authService: AuthService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {}

  ngOnDestroy(): void {}

  async onSubmit(form: NgForm) {
    const firstName = form.value.firstName;
    const lastName = form.value.lastName;
    const email = form.value.email;
    const password = form.value.password;
  
    try {
      const response: AxiosResponse<RegisterUserResponse> = 
        await this.authService.registerUser(firstName, lastName, email, password);    
        console.log(response)  
      const toastMessage = `Welcome ${firstName}`;
      this.toastService.toastSuccess(toastMessage, 3000);
    } catch (error: any) {
      console.log(error)
      this.toastService.toastAxiosError("register account", error, 5000);
    }
  }  
  
}
