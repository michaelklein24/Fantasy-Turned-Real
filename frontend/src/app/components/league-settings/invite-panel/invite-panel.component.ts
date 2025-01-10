import { Component, OnDestroy, OnInit } from '@angular/core';
import { InviteStatusEntryComponent } from './invite-status-entry/invite-status-entry.component';
import { InviteUserToLeagueFormComponent } from '../../../forms/invite-user-to-league-form/invite-user-to-league-form.component';
import { LeagueService } from '../../../services/league.service';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { GetInvitesForLeagueResponse, Invite } from '../../../shared/generated';
import { AxiosResponse } from 'axios';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-invite-panel',
  standalone: true,
  imports: [
    CommonModule,
    InviteStatusEntryComponent,
    InviteUserToLeagueFormComponent
  ],
  templateUrl: './invite-panel.component.html',
  styleUrl: './invite-panel.component.css'
})
export class InvitePanelComponent implements OnInit, OnDestroy {

  constructor(private leagueService: LeagueService, private route: ActivatedRoute) { }

  paramMapSub: Subscription | undefined;
  leagueId : string | null = null;

  approvedInvites : Invite[] = [];
  pendingInvites : Invite[] = [];
  declinedInvites : Invite[] = [];

  ngOnInit(): void {
    this.paramMapSub = this.route.parent!.paramMap.subscribe(params => {
      this.leagueId = params.get('leagueId');
    });

    if (this.leagueId) {
      this.fetchInvites()
    }
  }

  ngOnDestroy(): void {

  }

  async fetchInvites(): Promise<void> {
    const response : AxiosResponse<GetInvitesForLeagueResponse> = await this.leagueService.getInvitesForLeague(this.leagueId!);
    console.log(response.data)
    if (response.data.approved) {
      this.approvedInvites = response.data.approved;
    }
    if (response.data.pending) {
      this.pendingInvites = response.data.pending;
    }
    if (response.data.declined) {
      this.declinedInvites = response.data.declined;
    }
  }

  handleCreatedInvite(invite: Invite) {
    this.pendingInvites.push(invite);
  }

}
