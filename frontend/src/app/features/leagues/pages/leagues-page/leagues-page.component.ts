import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AxiosResponse } from 'axios';
import { GetLeaguesForUserResponse, League } from '../../../../../libs/generated';
import { ToastService } from '../../../../core/services/toast.service';
import { CreateLeagueFormComponent } from '../../../../forms/create-league-form/create-league-form.component';
import { ModalComponent } from '../../../../shared/components/modal/modal.component';
import { LeagueEntryComponent } from '../../components/league-entry/league-entry.component';
import { LeagueService } from '../../services/league.service';

@Component({
  selector: 'app-leagues-page',
  standalone: true,
  imports: [
    RouterModule, 
    CommonModule, 
    CreateLeagueFormComponent, 
    ModalComponent, 
    LeagueEntryComponent
  ],
  templateUrl: './leagues-page.component.html',
  styleUrl: './leagues-page.component.css'
})
export class LeaguesPageComponent implements OnInit {

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
