import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { CreateLeagueResponse, League } from '../../shared/generated';
import { LeagueService } from '../../services/league.service';
import { AxiosResponse } from 'axios';
import { ToastService } from '../../services/toast.service';

@Component({
  selector: 'app-create-league-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './create-league-form.component.html',
  styleUrl: './create-league-form.component.css'
})
export class CreateLeagueFormComponent {
  @Output() createLeague = new EventEmitter<League>();

  constructor(
    private leagueService: LeagueService,
    private toastService: ToastService
  ) {}

  async onSubmit(form: NgForm): Promise<void> {
    let league : League = {}

    console.log(form.value)


    try {
      const response: AxiosResponse<CreateLeagueResponse> = await this.leagueService.createLeague(form.value.leagueName);
      this.createLeague.emit(response.data.league);
    } catch (error: any) {
      console.log(error);
      this.toastService.toastAxiosError('create league', error, 5000);
    }
  }
}
