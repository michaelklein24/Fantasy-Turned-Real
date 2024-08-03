import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {

  @ViewChild('loginForm') loginForm: NgForm;

  submitted: boolean = false;

  constructor(private authService: AuthService) { }

  ngOnInit(): void { }

  onSubmit(): void {
    this.authService.login(this.loginForm.value.username, this.loginForm.value.password);
  }

}
