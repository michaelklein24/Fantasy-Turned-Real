import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { DashboardHeaderComponent } from '../../features/dashboard/dashboard-header/dashboard-header.component';
import { DashboardNavComponent } from '../../features/dashboard/dashboard-nav/dashboard-nav.component';

@Component({
  selector: 'app-dashboard-page',
  standalone: true,
  imports: [
    RouterModule,
    DashboardHeaderComponent,
    DashboardNavComponent,
  ],
  templateUrl: './dashboard-page.component.html',
  styleUrl: './dashboard-page.component.css'
})
export class DashboardPageComponent {
  
  isNavOpen = false;

  toggleNav() {
    this.isNavOpen = !this.isNavOpen;
  }
  

}
