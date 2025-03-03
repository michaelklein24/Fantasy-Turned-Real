import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { InviteUserToLeagueFormComponent } from '../../../../forms/invite-user-to-league-form/invite-user-to-league-form.component';
import { LeagueService } from '../../services/league.service';
import { InviteStatusEntryComponent } from '../invite-status-entry/invite-status-entry.component';
import { Invite } from '../../../../libs/generated/typescript-angular';
import { ToastService } from '../../../../services/toast.service';

@Component({
  selector: 'app-invite-panel',
  standalone: true,
  imports: [
    CommonModule,
    InviteStatusEntryComponent,
    InviteUserToLeagueFormComponent,
  ],
  templateUrl: './invite-panel.component.html',
  styleUrl: './invite-panel.component.css',
})
export class InvitePanelComponent implements OnInit, OnDestroy {
  $invites: Observable<Invite[]> | null = null;
  paramMapSub: Subscription | undefined;
  leagueId: string | null = null;

  approvedInvites: Invite[] = [];
  pendingInvites: Invite[] = [];
  declinedInvites: Invite[] = [];

  constructor(
    private leagueService: LeagueService,
    private route: ActivatedRoute,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.paramMapSub = this.route.parent!.paramMap.subscribe((params) => {
      this.leagueId = params.get('leagueId');
    });

    if (this.leagueId) {
      this.fetchInvites();
    }
  }

  ngOnDestroy(): void {
    this.paramMapSub?.unsubscribe();
  }

  fetchInvites(): void {
    if (this.leagueId) {
      this.$invites = this.leagueService.getInvitesForLeague(this.leagueId);

      // Subscribe to the observable to separate invites
      this.$invites.subscribe({
        next: (invites: Invite[]) => {
          this.approvedInvites = invites.filter(
            (invite) => invite.status === 'APPROVED'
          );
          this.pendingInvites = invites.filter(
            (invite) => invite.status === 'PENDING'
          );
          this.declinedInvites = invites.filter(
            (invite) => invite.status === 'DECLINED'
          );
        },
        error: (error) => {
          this.toastService.toastApiError('Get invites failed', error, 5000);
        },
      });
    }
  }
}
