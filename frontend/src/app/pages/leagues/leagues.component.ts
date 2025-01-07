import { CommonModule, NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { League } from '../../shared/generated';
import { LeagueService } from '../../services/league.service';
import { CreateLeagueFormComponent } from '../../forms/create-league-form/create-league-form.component';
import { ModalComponent } from '../../shared/modal/modal.component';
import { FormGroup, NgForm } from '@angular/forms';

@Component({
  selector: 'app-leagues',
  standalone: true,
  imports: [RouterModule, CommonModule, CreateLeagueFormComponent, ModalComponent],
  templateUrl: './leagues.component.html',
  styleUrl: './leagues.component.css'
})
export class LeaguesComponent implements OnInit {

  leagues : League[] = [];
  newLeagueName: string = '';
  isModalOpen = false;

  constructor(
    private leagueService: LeagueService,
  ) {}

  ngOnInit(): void {
    this.fetchLeagues();
  }

  fetchLeagues(): void {
    // Replace this with a real service call
    this.leagues = [
      { leagueId: "1", name: 'Premier League'},
      { leagueId: "2", name: 'Fantasy League 2024' },
      { leagueId: "3", name: 'Champions League' },
      { leagueId: "4", name: 'Survivor Pool' },
    ];
  }

  openModal(): void {
    this.isModalOpen = true;
  }

  closeModal(): void {
    this.isModalOpen = false;
  }

  handleLeagueCreation(league: League): void {
    // Handle the form data (e.g., save it to a database)
    this.closeModal();
  }
  


}
