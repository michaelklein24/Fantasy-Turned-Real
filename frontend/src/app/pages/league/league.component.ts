import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { League } from '../../shared/generated';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs';

@Component({
  selector: 'app-league',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './league.component.html',
  styleUrl: './league.component.css',
})
export class LeagueComponent implements OnInit {
  constructor(private route: ActivatedRoute) {}

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
    let league = this.route.paramMap
      .pipe(map(() => window.history.state))
      .subscribe((res) => console.log(res));
  }
}
