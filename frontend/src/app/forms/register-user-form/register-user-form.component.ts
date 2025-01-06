import { Component, OnDestroy, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormsModule, NgForm } from '@angular/forms';
import { ToastService } from '../../services/toast.service';
import apiClient, {
  RegisterUserRequest,
  RegisterUserResponse,
} from '../../shared/ApiClient';
import { AxiosError } from 'axios';
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
    const request: RegisterUserRequest = { ...form.value };
  
    try {
      // Await the API call
      const response: AxiosResponse<RegisterUserResponse> =
        await apiClient.auth.register(request);
      
      // Handle successful registration
      const toastMessage = `Welcome ${request.firstName}`;
      this.toastService.toastSuccess(toastMessage, 1000);
    } catch (error: any) {
      this.toastService.toastAxiosError("register account", error, 3000);
    }
  }  
  
}
