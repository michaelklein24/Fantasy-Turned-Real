import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Subscription } from 'rxjs';
import { League } from '../../../../../libs/generated';

@Component({
  selector: 'app-league-page',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './league-page.component.html',
  styleUrl: './league-page.component.css',
})
export class LeaguePageComponent implements OnInit, OnDestroy {
  constructor(private route: ActivatedRoute) {}

  paramSub : Subscription = new Subscription();

  leagueId: string | null = null;
  league: League = {}

  scoreboard = [
    { name: 'John Doe', score: 250 },
    { name: 'Jane Smith', score: 230 },
    { name: 'Alex Johnson', score: 210 },
  ];

  participants = [
    { name: 'John Doe' },
    { name: 'Jane Smith' },
    { name: 'Alex Johnson' },
  ];

  surveys = [
    { episodeNumber: 1, completed: true },
    { episodeNumber: 2, completed: false },
    { episodeNumber: 3, completed: true },
  ];

  ngOnInit(): void {
    this.paramSub = this.route.paramMap
      .subscribe((params) => this.leagueId = params.get('leagueId'));
  }

  ngOnDestroy(): void {
    this.paramSub.unsubscribe();
  }
}
