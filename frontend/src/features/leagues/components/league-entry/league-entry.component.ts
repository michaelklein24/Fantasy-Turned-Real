import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { League } from '../../../../libs/generated/typescript-angular';

@Component({
  selector: 'app-league-entry',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './league-entry.component.html',
  styleUrl: './league-entry.component.css',
})
export class LeagueEntryComponent {
  @Input() public league!: League;

  constructor(private router: Router, private route: ActivatedRoute) {}

  navigateToLeague() {
    this.router.navigate(['dashboard', 'league', this.league.leagueId], {
      queryParamsHandling: 'preserve',
    });
  }
}
