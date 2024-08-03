import { CommonModule } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  @ViewChild('registerForm') registerForm: NgForm;

  constructor(private authService: AuthService) {}

  onSubmit(): void {
    let firstName = this.registerForm.value.firstName;
    let lastName = this.registerForm.value.lastName;
    let username = this.registerForm.value.lastName;
    let email = this.registerForm.value.email;
    let password = this.registerForm.value.password;
    this.authService.register(firstName, lastName, username, email, password);
  }
}
