import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-dashboard-header',
  standalone: true,
  imports: [],
  templateUrl: './dashboard-header.component.html',
  styleUrl: './dashboard-header.component.css'
})
export class DashboardHeaderComponent implements OnInit, OnDestroy{

  headerText: string = "Dashboard"
  routerEventsSub: Subscription | null = null;

  @Output() toggleMenu = new EventEmitter<void>();

  constructor(
    private router: Router,
    private route: ActivatedRoute
  ) { }


  ngOnInit() {
    this.routerEventsSub = this.router.events.subscribe(() => {
      this.updateHeaderText();
    });
  }

  ngOnDestroy(): void {
    this.routerEventsSub?.unsubscribe();
  }

  private updateHeaderText() {
    const currentRoute = this.router.url;

    const routeHeaders : { [route: string]: string } = {
      '/dashboard': 'Dashboard',
      '/dashboard/league': 'Leagues',
      '/dashboard/settings': 'Settings',
    };

    this.headerText = routeHeaders[currentRoute] || 'Default Header';
  }

  toggleAside() {
    this.toggleMenu.emit();
  }

}
