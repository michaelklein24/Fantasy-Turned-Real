import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AxiosResponse } from 'axios';
import { Subscription } from 'rxjs';
import { Invite, InviteUserToLeagueResponse } from '../../../libs/generated';
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

  @Output()
  inviteCreated: EventEmitter<Invite> = new EventEmitter<Invite>();

  constructor(
    private leagueService : LeagueService, 
    private route: ActivatedRoute,
    private toastService : ToastService
  ) {
    
  }

  ngOnInit(): void {
    this.paramSub = this.route.parent?.paramMap.subscribe((params) => this.leagueId = params.get('leagueId'))
  }
  ngOnDestroy(): void {
    this.paramSub?.unsubscribe();
  }

  async onSubmit(form: NgForm) {
    try {
      const email : string = form.value.email;
      const response : AxiosResponse<InviteUserToLeagueResponse> = await this.leagueService.inviteUserToLeague(this.leagueId!, email)
      this.inviteCreated.emit(response.data.invite);
    } catch (error) {
      this.toastService.toastAxiosError('invite user', error, 5000);
    }

  }

}
