import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { AxiosResponse } from 'axios';
import { CreateLeagueResponse, League } from '../../../libs/generated';
import { ToastService } from '../../core/services/toast.service';
import { LeagueService } from '../../features/leagues/services/league.service';
import { AxiosError } from 'axios';

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
    if (form.invalid) {
      this.toastService.toastError('Please fill in all the required fields', 3000);
      return;
    }
  
    const { leagueName, seasonNumber } = form.value;
  
    try {
      const response: CreateLeagueResponse = await this.leagueService.createLeague(leagueName, seasonNumber);
      this.createLeague.emit(response.league);
      this.toastService.toastSuccess(`League ${leagueName} created successfully`, 5000);
    } catch (error: AxiosError | any) {
      if (error.isAxiosError) {
        console.error('Axios error:', error.response?.data);
        this.toastService.toastAxiosError('Create league failed', error, 5000);
      } else {
        console.error('Unexpected error:', error);
        this.toastService.toastError('An unexpected error occurred. Please try again later.', 5000);
      }
    }
  }
}
