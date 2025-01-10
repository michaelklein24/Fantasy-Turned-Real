import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SessionService } from '../../../auth/services/session.service';

@Component({
  selector: 'app-dashboard-page',
  standalone: true,
  imports: [
    RouterModule
  ],
  templateUrl: './dashboard-page.component.html',
  styleUrl: './dashboard-page.component.css'
})
export class DashboardPageComponent {

  constructor(
    private sessionService: SessionService,
    private router: Router
  ) {}

  onLogout() {
    this.sessionService.clearSession();
    this.router.navigate(["/"])
  }

}
