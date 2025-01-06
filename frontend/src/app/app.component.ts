import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegisterUserFormComponent } from './forms/register-user-form/register-user-form.component';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { ToastComponent } from './shared/toast/toast.component';
import { CommonModule } from '@angular/common';
import { LoginUserFormComponent } from './forms/login-user-form/login-user-form.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    RegisterUserFormComponent,
    LoginUserFormComponent,
    NavbarComponent,
    ToastComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
