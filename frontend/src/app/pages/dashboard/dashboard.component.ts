import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SessionService } from '../../services/session.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  constructor(
    private sessionService: SessionService,
    private router: Router
  ) {}

  onLogout() {
    this.sessionService.clearSession();
    this.router.navigate(["/"])
  }

}
