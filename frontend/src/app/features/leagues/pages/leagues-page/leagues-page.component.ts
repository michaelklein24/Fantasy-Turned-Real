import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Observable } from 'rxjs';
import { League } from '../../../../../libs/generated/typescript-angular';
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

  leagues$: Observable<League[]> | null = null;
  leagues: League[] = [];

  isModalOpen = false;

  constructor(
    private leagueService: LeagueService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.leagues$ = this.leagueService.getLeaguesForUser();
    this.fetchLeagues()
  }

  fetchLeagues(): void {
    this.leagues$!.subscribe({
      next: (leagues: League[]) => {
        console.log(leagues)
        if (leagues) {
          this.leagues = leagues;
        } else {
          this.leagues = [];
        }
      },
      error: (error) => {
        this.toastService.toastApiError('Get leagues for user', error, 5000);
      }
    });
  }

  openModal(): void {
    this.isModalOpen = true;
  }

  closeModal(): void {
    this.isModalOpen = false;
    console.log('test')
  }
}
