import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SessionService } from '../../services/session.service';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule, CommonModule],
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

  // Listen for clicks anywhere on the document
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    // Check if the click target is outside the dropdown
    const dropdown = document.querySelector('.absolute');
    const profileImage = document.querySelector('img[alt="User Profile"]');
    
    if (this.showUserMenu && dropdown && !dropdown.contains(event.target as Node) && !profileImage?.contains(event.target as Node)) {
      this.closeUserMenu();
    }
  }

  onLogout(): void {
    this.sessionService.clearSession();
    this.showUserMenu = false;
    this.router.navigate(["/"])
  }

}
