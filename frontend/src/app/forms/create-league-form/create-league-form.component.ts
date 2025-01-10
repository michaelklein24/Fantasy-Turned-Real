import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { AxiosResponse } from 'axios';
import { CreateLeagueResponse, League } from '../../../libs/generated';
import { ToastService } from '../../core/services/toast.service';
import { LeagueService } from '../../features/leagues/services/league.service';

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
      const leagueName : string = form.value.leagueName;
      const seasonNumber : number = form.value.seasonNumber;
      const response: AxiosResponse<CreateLeagueResponse> = await this.leagueService.createLeague(leagueName, seasonNumber);
      this.createLeague.emit(response.data.league);
    } catch (error: any) {
      console.log(error);
      this.toastService.toastAxiosError('create league', error, 5000);
    }
  }
}
