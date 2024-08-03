import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { AuthControllerService, LoginUserRequest, LoginUserResponse } from '../../../swagger';
import { HttpResponse } from '@angular/common/http';
import { TokenService } from '../../../services/token.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit, OnDestroy {

  @ViewChild('loginForm') loginForm: NgForm;
  submitted: boolean = false;
  loginSub: Subscription;

  constructor(private authService: AuthControllerService, private tokenService: TokenService, private router: Router, private toastService: ToastrService) { }

  ngOnInit(): void { }

  ngOnDestroy(): void {
    this.loginSub?.unsubscribe();
  }

  onSubmit(): void {
    let loginUserRequest: LoginUserRequest = {};
    loginUserRequest.username = this.loginForm.value.username;
    loginUserRequest.password = this.loginForm.value.password;

    this.loginSub = this.authService.login(loginUserRequest, 'response').subscribe({
      next: (response: HttpResponse<LoginUserResponse>) => {
        if (response.status === 200) {
          this.tokenService.saveToken(response.body!.accessToken!);
          this.router.navigate(["/league"]);
        } else {
          this.toastService.error("Unable to Login. An unknown error occurred.");
          this.loginForm.reset();
        }
      },
      error: (error: Error) => {
        this.toastService.error("Unable to Login. An unknown error occurred.");
        this.loginForm.reset();
      }
    });
  }

}
