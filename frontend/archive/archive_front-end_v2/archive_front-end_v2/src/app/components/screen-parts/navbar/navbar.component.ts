import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';
import { SessionService } from '../../../services/session.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit, OnDestroy {

  constructor(private sessionService: SessionService) { }

  isLoggedInSub: Subscription;
  isLoggedIn: boolean;

  userIdSub: Subscription;
  userId: string;

  ngOnInit(): void {
    this.isLoggedInSub = this.sessionService.loginStatus$.subscribe((isLoggedIn: boolean) => this.isLoggedIn = isLoggedIn);
    this.userIdSub = this.sessionService.userId$.subscribe((userId: string | null) => {
      if (userId != null) {
        this.userId = userId;
      }
    });
  }

  ngOnDestroy(): void {
    this.isLoggedInSub.unsubscribe();
  }

  onLogout(): void {
    this.sessionService.clearSession();
  }
}
