import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ToastService } from '../../core/services/toast.service';
import { AuthService } from '../../features/auth/services/auth.service';

@Component({
  selector: 'app-register-user-form',
  standalone: true,
  imports: [
    FormsModule,
    RouterModule
  ],
  templateUrl: './register-user-form.component.html',
  styleUrls: ['./register-user-form.component.css'],
})
export class RegisterUserFormComponent implements OnInit, OnDestroy {
  
  constructor(
    private authService: AuthService,
    private toastService: ToastService,
    private router: Router
  ) {}

  ngOnInit(): void {}

  ngOnDestroy(): void {}

  onSubmit(form: NgForm): void {
    if (form.invalid) {
      this.toastService.toastError('Please fill in all the required fields', 3000);
      return;
    }

    const { firstName, lastName, email, password } = form.value;

    this.authService.registerUser(firstName, lastName, email, password).subscribe({
      next: () => {
        this.router.navigate(['/dashboard']);
      },
      error: (error: any) => {
        this.toastService.toastApiError('register account', error, 5000);
      }
    });
  }
  
}
