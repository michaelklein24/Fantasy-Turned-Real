import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Invite } from '../../../libs/generated/typescript-angular';
import { ToastService } from '../../core/services/toast.service';
import { LeagueService } from '../../features/leagues/services/league.service';

@Component({
  selector: 'app-invite-user-to-league-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './invite-user-to-league-form.component.html',
  styleUrl: './invite-user-to-league-form.component.css'
})
export class InviteUserToLeagueFormComponent implements OnInit, OnDestroy {

  paramSub: Subscription | undefined;
  leagueId: string | null = null;

  constructor(
    private leagueService: LeagueService, 
    private route: ActivatedRoute,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.paramSub = this.route.parent?.paramMap.subscribe((params) => this.leagueId = params.get('leagueId'));
  }

  ngOnDestroy(): void {
    this.paramSub?.unsubscribe();
  }

  onSubmit(form: NgForm) {
    const email: string = form.value.email;
    if (this.leagueId) {
      this.leagueService.inviteUserToLeague(this.leagueId, email).subscribe({
        next: (invite : Invite) => {
          this.toastService.toastSuccess(`${invite.invitee?.firstName} was successfully invited`, 5000);
        },
        error: (error) => {
          // Handle error using ToastService
          this.toastService.toastApiError('Failed to Invite User', error, 5000);
        }
      });
    }
  }
}
