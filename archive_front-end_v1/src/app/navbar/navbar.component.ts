import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../auth/auth.service';
import { CommonModule } from '@angular/common';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit, OnDestroy {

  constructor(private authService: AuthService) { }

  isLoggedInSub: Subscription;
  isLoggedIn: boolean;

  ngOnInit(): void {
    this.isLoggedInSub = this.authService.isLoggedIn().subscribe((isLoggedIn) => this.isLoggedIn = isLoggedIn);
  }

  ngOnDestroy(): void {
    this.isLoggedInSub.unsubscribe();
  }

  onLogout(): void {
    this.authService.logout();
  }
}
