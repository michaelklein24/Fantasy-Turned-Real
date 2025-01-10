import { CommonModule } from '@angular/common';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { League } from '../../shared/generated';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { map, Subscription } from 'rxjs';

@Component({
  selector: 'app-league',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './league.component.html',
  styleUrl: './league.component.css',
})
export class LeagueComponent implements OnInit, OnDestroy {
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
