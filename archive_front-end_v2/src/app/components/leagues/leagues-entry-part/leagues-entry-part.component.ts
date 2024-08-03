import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { RouterModule } from '@angular/router';
import { LeagueSummaryWithUserDetails } from '../../../swagger';

@Component({
  selector: 'app-leagues-entry-part',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './leagues-entry-part.component.html',
  styleUrl: './leagues-entry-part.component.css'
})
export class LeaguesEntryPartComponent {
  @Input() public league: LeagueSummaryWithUserDetails;
  @Output() selectLeague: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }
  
  onSelectLeague() {
    this.selectLeague.emit(this.league.leagueId);
  }
}
