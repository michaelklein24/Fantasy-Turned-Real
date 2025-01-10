import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { LeagueService } from '../../services/league.service';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { AxiosResponse } from 'axios';
import { Invite, InviteUserToLeagueResponse } from '../../shared/generated';
import { ToastService } from '../../services/toast.service';

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
