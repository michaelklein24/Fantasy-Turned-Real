import { CommonModule } from '@angular/common';
import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';
import { SessionService } from '../../../features/auth/services/session.service';
import { NotificationsComponent } from '../notifications/notifications.component';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    RouterModule, 
    CommonModule,
    NotificationsComponent
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit, OnDestroy {

  constructor(
    private sessionService: SessionService,
    private router: Router
  ) { }

  isLoggedInSub: Subscription | undefined;
  isLoggedIn: boolean = false;
  showUserMenu: boolean = false;

  ngOnInit(): void {
    this.isLoggedInSub = this.sessionService.loginStatus$.subscribe((isLoggedIn: boolean) => this.isLoggedIn = isLoggedIn);
    };

  ngOnDestroy(): void {
    this.isLoggedInSub!.unsubscribe();
  }

  toggleUserMenu(): void {
    this.showUserMenu = !this.showUserMenu;
  }

  closeUserMenu() {
    this.showUserMenu = false;
  }

  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    const dropdown = document.querySelector('.absolute');
    const profileImage = document.querySelector('img[alt="User Profile"]');
    const menuItem = dropdown?.querySelector('a, button, li'); // Target menu items like <a> or <button>
  
    // Check if the click target is outside the dropdown and profile image
    if (this.showUserMenu && dropdown && !dropdown.contains(event.target as Node) && !profileImage?.contains(event.target as Node)) {
      this.closeUserMenu();
    }
  
    // Close the menu if the target is a menu item
    if (this.showUserMenu && menuItem && menuItem.contains(event.target as Node)) {
      this.closeUserMenu();
    }
  }

  onLogout(): void {
    this.sessionService.clearSession();
    this.showUserMenu = false;
    this.router.navigate(["/"])
  }

  navigateHome(): void {
    this.router.navigate(["/"])
  }

}
