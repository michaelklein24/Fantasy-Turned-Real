import { Component, ElementRef, Input, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Params, Router, RouterModule } from '@angular/router';

import { CommonModule } from '@angular/common';
import { LeagueNotificationPartComponent } from './league-notification-part/league-notification-part.component';
import { GetLeagueDetailsByIdForUserResponse, LeagueControllerService, LeagueSummaryWithUserDetails } from '../../../swagger';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-league',
  standalone: true,
  imports: [CommonModule, RouterModule, LeagueNotificationPartComponent],
  templateUrl: './league.component.html',
  styleUrl: './league.component.css'
})
export class LeagueComponent implements OnInit, OnDestroy {

  private leagueSub: Subscription;

  @Input()
  public leagueSummary: LeagueSummaryWithUserDetails;

  public notificationClosed: boolean = false;
  public openSurvey: boolean = false;

  constructor(private leagueService: LeagueControllerService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    let leagueId: string | null;

    leagueId = this.route.snapshot.paramMap.get('leagueId');

    if (leagueId) {
      this.leagueSub = this.leagueService.getLeagueDetailsById(leagueId, 'response').subscribe({
        next: (response: HttpResponse<GetLeagueDetailsByIdForUserResponse>) => {
          console.log('TESTESTEST');
        },
        error: () => { }
      });
    } else {
      console.error("Unable to parse userId and leagueId from query params.");
    }
  }

  ngOnDestroy(): void {
    this.leagueSub?.unsubscribe();
  }

  onClose(): void {
    this.notificationClosed = true;
  }

  onCloseSurvey(): void {
    this.openSurvey = false;
  }

  onOpenSurvey(): void {
    console.log('test');
    this.notificationClosed = true;
    this.openSurvey = true;
  }
}
