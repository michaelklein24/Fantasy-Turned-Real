import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

import { Show } from '../../libs/generated/typescript-angular';
import { DropdownComponent, DropdownMenuItem } from '../../shared/components/dropdown/dropdown.component';
import { ToastService } from '../../services/toast.service';
import { LeagueService } from '../../features/leagues/services/league.service';
import { ShowService } from '../../features/shows/services/show.service';

@Component({
  selector: 'app-create-league-form',
  standalone: true,
  imports: [FormsModule, DropdownComponent],
  templateUrl: './create-league-form.component.html',
  styleUrl: './create-league-form.component.css',
})
export class CreateLeagueFormComponent implements OnInit {
  @Output() leagueCreated: EventEmitter<void> = new EventEmitter();

  showMenuItems: DropdownMenuItem[] = [];
  seasonMenuItems: DropdownMenuItem[] = [];
  selectedShow: DropdownMenuItem = { label: 'Survivor', value: 'SURVIVOR' };
  selectedSeason: DropdownMenuItem = { label: 'Season 47', value: '47' };
  private shows: Show[] = [];

  constructor(
    private leagueService: LeagueService,
    private toastService: ToastService,
    private showService: ShowService
  ) {}

  buildShowMenuItems(shows: Show[]) {
    this.showMenuItems = shows.map((show: Show) => ({
      value: show.name!,
      label: show.name!,
    }));
  }

  buildSeasonMenuItems(selectedShowName: string) {
    const selectedShow = this.shows.find(
      (show) => show.name === selectedShowName
    );
    this.seasonMenuItems =
      selectedShow?.seasons?.map((season) => ({
        label: `Season ${season.sequence}`,
        value: String(season.sequence),
      })) || [];
  }

  ngOnInit(): void {
    this.showService.getShows().subscribe((shows: Show[]) => {
      this.shows = shows;
      this.buildShowMenuItems(shows);
      this.selectedShow = this.showMenuItems[0];
      if (shows.length > 0) {
        this.buildSeasonMenuItems(shows[0].name!);
        this.selectedSeason = this.seasonMenuItems[0];
      }
    });
  }

  onShowSelection(selectedItem: DropdownMenuItem[]): void {
    this.selectedShow = selectedItem[0];
    this.buildSeasonMenuItems(selectedItem[0].value);
  }

  onSeasonSelection(selectedItem: DropdownMenuItem[]): void {
    this.selectedSeason = selectedItem[0];
  }

  onSubmit(form: NgForm): void {
    if (form.invalid) {
      this.toastService.toastError(
        'Please fill in all the required fields',
        3000
      );
      return;
    }
    const { leagueName, show, season } = form.value;

    this.leagueService.createLeague(leagueName, show, season).subscribe({
      next: () => {
        this.leagueCreated.emit();
        this.toastService.toastSuccess(
          `League ${leagueName} created successfully`,
          5000
        );
      },
      error: (error: Error) => {
        this.toastService.toastApiError('create league', error, 5000);
      },
    });
  }
}
