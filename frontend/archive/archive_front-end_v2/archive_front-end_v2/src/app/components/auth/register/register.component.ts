import { CommonModule } from '@angular/common';
import { HttpResponse } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { TokenService } from '../../../services/token.service';
import { AuthControllerService, RegisterUserRequest, RegisterUserResponse } from '../../../swagger';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  @ViewChild('registerForm') registerForm: NgForm;

  constructor(private authService: AuthControllerService, private tokenService: TokenService, private router: Router, private toastService: ToastrService) { }

  registerSub: Subscription;

  onSubmit(): void {
    let firstName = this.registerForm.value.firstName;
    let lastName = this.registerForm.value.lastName;
    let username = this.registerForm.value.lastName;
    let email = this.registerForm.value.email;
    let password = this.registerForm.value.password;

    let registerUserRequest: RegisterUserRequest = {};
    registerUserRequest.firstName = firstName;
    registerUserRequest.lastName = lastName;
    registerUserRequest.email = email;
    registerUserRequest.username = username;
    registerUserRequest.password = password;

    this.registerSub = this.authService.register(registerUserRequest, 'response').subscribe(
      {
        next: (response: HttpResponse<RegisterUserResponse>) => {
          if (response.status === 200) {
            this.tokenService.saveToken(response.body!.accessToken!);
            this.router.navigate(["/"]);
          } else {
            this.toastService.error("Unable to Register account An unknown error occurred.");
            this.registerForm.reset();
          }
        },
        error: (error: Error) => {
          this.toastService.error("Unable to Register account An unknown error occurred.");
          this.registerForm.reset();
        }
      });
  }
}
