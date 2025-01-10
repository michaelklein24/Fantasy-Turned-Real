import { CommonModule, NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { GetLeaguesForUserResponse, League } from '../../shared/generated';
import { LeagueService } from '../../services/league.service';
import { CreateLeagueFormComponent } from '../../forms/create-league-form/create-league-form.component';
import { ModalComponent } from '../../shared/modal/modal.component';
import { AxiosResponse } from 'axios';
import { ToastService } from '../../services/toast.service';
import { LeagueEntryComponent } from '../../components/dashboard/leagues/league-entry/league-entry.component';

@Component({
  selector: 'app-leagues',
  standalone: true,
  imports: [
    RouterModule, 
    CommonModule, 
    CreateLeagueFormComponent, 
    ModalComponent, 
    LeagueEntryComponent
  ],
  templateUrl: './leagues.component.html',
  styleUrl: './leagues.component.css'
})
export class LeaguesComponent implements OnInit {

  leagues : League[] = [];
  newLeagueName: string = '';
  isModalOpen = false;

  constructor(
    private leagueService: LeagueService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.fetchLeagues();
  }

  async fetchLeagues(): Promise<void> {
    try {
      const getLeaguesForUserResponse: AxiosResponse<GetLeaguesForUserResponse> = await this.leagueService.getLeaguesForUser();
      if (getLeaguesForUserResponse.data.leagues) {
        this.leagues = getLeaguesForUserResponse.data.leagues;
      } else {
        this.leagues = [];
      }
    } catch (error) {
      this.toastService.toastAxiosError('get leagues for user', error, 5000)
      this.leagues = [];
    }
  }

  openModal(): void {
    this.isModalOpen = true;
  }

  closeModal(): void {
    this.isModalOpen = false;
  }

  handleLeagueCreation(league: League): void {
    this.leagues.unshift(league);
    this.closeModal();
  }

  disapproveInvite(name: string) {

  }

  approveInvite(name: string) {
    
  }

}
