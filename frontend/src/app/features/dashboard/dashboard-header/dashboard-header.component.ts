import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { League } from '../../../../libs/generated/typescript-angular';
import { LeagueService } from '../../leagues/services/league.service';

@Component({
  selector: 'app-dashboard-header',
  standalone: true,
  imports: [],
  templateUrl: './dashboard-header.component.html',
  styleUrls: ['./dashboard-header.component.css'],
})
export class DashboardHeaderComponent implements OnInit, OnDestroy {
  headerText: string = "Dashboard";
  routerEventsSub: Subscription | null = null;
  leagueSubscription: Subscription | null = null;

  @Output() toggleMenu = new EventEmitter<void>();

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private leagueService: LeagueService
  ) {}

  ngOnInit() {
    // Listen for route changes to update header text
    this.routerEventsSub = this.router.events.subscribe(() => {
      this.updateHeaderText();
    });
    // Initial header update
    this.updateHeaderText();
  }

  ngOnDestroy(): void {
    this.routerEventsSub?.unsubscribe();
    this.leagueSubscription?.unsubscribe();
  }

  private updateHeaderText() {
    const currentRoute = this.router.url;

    // Check if the route matches the league pattern
    const leagueViewRoutePattern = currentRoute.match(/\/dashboard\/league\/([a-f0-9-]+)/); // Extract leagueId
    if (leagueViewRoutePattern) {
      const leagueId = leagueViewRoutePattern[1]; // Extract leagueId from route
      this.leagueSubscription?.unsubscribe(); // Clean up any previous subscription
      this.leagueSubscription = this.leagueService.getLeaguesForUser().subscribe({
        next: (leagues: League[]) => {
          const league = leagues.find((l) => l.leagueId === leagueId);
          this.headerText = league ? league.name! : 'League Not Found';
        },
        error: (err: any) => {
          console.error('Error fetching leagues:', err);
          this.headerText = 'League Not Found';
        },
      });
    } else {
      this.headerText = 'Dashboard'; // Default header
    }
  }

  toggleAside() {
    this.toggleMenu.emit();
  }
}
