import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
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

  @Output() leagueCreated : EventEmitter<void> = new EventEmitter();

  constructor(
    private leagueService: LeagueService,
    private toastService: ToastService
  ) {}

  onSubmit(form: NgForm): void {
    if (form.invalid) {
      this.toastService.toastError('Please fill in all the required fields', 3000);
      return;
    }
  
    const { leagueName, seasonNumber } = form.value;
  
    this.leagueService.createLeague(leagueName, seasonNumber).subscribe({
      next: (response) => {
        this.leagueCreated.emit(); 
        this.toastService.toastSuccess(`League ${leagueName} created successfully`, 5000);
      },
      error: (error) => {
        this.toastService.toastApiError('create league', error, 5000);
      },
    });
  }
  
}
