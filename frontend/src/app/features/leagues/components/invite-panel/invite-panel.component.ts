import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AxiosResponse } from 'axios';
import { Subscription } from 'rxjs';
import { GetInvitesForLeagueResponse, Invite } from '../../../../../libs/generated';
import { InviteUserToLeagueFormComponent } from '../../../../forms/invite-user-to-league-form/invite-user-to-league-form.component';
import { LeagueService } from '../../services/league.service';
import { InviteStatusEntryComponent } from '../invite-status-entry/invite-status-entry.component';

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
handleCreatedInvite($event: Invite) {
throw new Error('Method not implemented.');
}

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
    const response : GetInvitesForLeagueResponse = await this.leagueService.getInvitesForLeague(this.leagueId!);
    console.log(response)
    if (response.approved) {
      this.approvedInvites = response.approved;
    }
    if (response.pending) {
      this.pendingInvites = response.pending;
    }
    if (response.declined) {
      this.declinedInvites = response.declined;
    }
  }

  handleCreateInvite(invite : Invite) {

  }
  
}
