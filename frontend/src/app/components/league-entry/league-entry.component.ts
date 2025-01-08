import { Component, Input } from '@angular/core';
import { League } from '../../shared/generated';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-league-entry',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './league-entry.component.html',
  styleUrl: './league-entry.component.css'
})
export class LeagueEntryComponent {

  @Input() public league! : League;

  constructor(private router: Router, private route: ActivatedRoute) {}

  navigateToLeague() {
    this.router.navigate([this.league.leagueId], {
      state: { league : this.league,}, 
      relativeTo: this.route
    })
  }

}
