import { CommonModule } from '@angular/common';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Subscription } from 'rxjs';
import { IFormComponent } from '../../model/form.interface';
import { DialogService } from '../../services/dialog.service';
import { SessionService } from '../../services/session.service';
import { CreateLeagueResponse, GetLeagueSummariesForUser, LeagueBody, LeagueControllerService, LeagueSummaryWithUserDetails } from '../../swagger';
import { LeaguesEntryPartComponent } from './leagues-entry-part/leagues-entry-part.component';
import { NewLeagueFormComponent } from './new-league-form/new-league-form.component';

@Component({
  selector: 'app-leagues',
  standalone: true,
  imports: [CommonModule, RouterModule, LeaguesEntryPartComponent, NewLeagueFormComponent],
  templateUrl: './leagues.component.html',
  styleUrl: './leagues.component.css'
})
export class LeaguesComponent implements OnInit, OnDestroy {

  private userIdSub: Subscription;
  private leagueSub: Subscription;
  private closeDialogSub: Subscription;
  public userId: string;
  public leagues: LeagueSummaryWithUserDetails[];
  public completedLeagues: LeagueSummaryWithUserDetails[];

  public selectedTab: string = 'current';

  constructor(private sessionService: SessionService,
    private leagueService: LeagueControllerService,
    private dialogService: DialogService,
    private router: Router,
    private route: ActivatedRoute,
    private toastService: ToastrService
  ) { }

  ngOnInit(): void {
    this.userIdSub = this.sessionService.userId$.subscribe((userId: string | null) => {
      if (userId != null) {
        this.userId = userId;
      }
    });
    this.leagueSub = this.leagueService.getLeaguesForUser('response').subscribe(
      {
        next: (response: HttpResponse<GetLeagueSummariesForUser>) => {
          this.leagues = response.body!.currentLeagues!;
          this.completedLeagues = response.body!.completedLeagues!;
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);
          // this.toastService.error("Unable to Retrieve Leagues. Status Code: " + error.status);
        }
      });
  }

  ngOnDestroy(): void {
    this.userIdSub?.unsubscribe();
    this.leagueSub?.unsubscribe();
    this.closeDialogSub?.unsubscribe();
  }

  onSelectTab(tab: string): void {
    this.selectedTab = tab;
  }

  onSelectLeague(leagueId: number): void {
    this.router.navigate([String(leagueId)], { relativeTo: this.route });
  }

  onNewLeague(): void {
    const dialogRef: MatDialogRef<IFormComponent> = this.dialogService.openFormDialog(NewLeagueFormComponent);

    this.closeDialogSub = dialogRef.afterClosed().subscribe(form => {
      console.log('Form was Submitted or Closed');
    });
  }


}
