import { Component, OnInit } from '@angular/core';
import { LineChartComponent } from './line-chart/line-chart.component';

@Component({
  selector: 'app-scoreboard',
  standalone: true,
  imports: [LineChartComponent],
  templateUrl: './scoreboard.component.html',
  styleUrl: './scoreboard.component.css'
})
export class ScoreboardComponent implements OnInit {

  ngOnInit(): void {

  }

}
