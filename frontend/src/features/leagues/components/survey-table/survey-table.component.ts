import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { League, Survey } from '../../../../libs/generated/typescript-angular';
import { SurveyService } from '../../services/survey.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-survey-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './survey-table.component.html',
  styleUrl: './survey-table.component.css',
})
export class SurveyTableComponent implements OnInit, OnDestroy {
  surveys: Survey[] = [];
  leagueId: string | undefined;

  paramsSubscription: Subscription | undefined;

  constructor(
    private surveyService: SurveyService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.paramsSubscription = this.route.parent!.params.subscribe(
      (params: Params) => {
        this.leagueId = params['leagueId'];
      }
    );

    this.surveyService
      .getSurveysForLeague(this.leagueId!)
      .subscribe((surveys: Survey[]) => {
        console.log(surveys);
        this.surveys = surveys;
      });
  }

  ngOnDestroy(): void {
    this.paramsSubscription?.unsubscribe();
  }

  handleSurveyClick(surveyId: string): void {
    console.log('Navigating with surveyId', surveyId);

    // Get the current path, including the parent route
    const currentPath = this.route.snapshot.url
      .map((segment) => segment.path)
      .join('/');

    // Navigate relative to the parent path and append the surveyId
    this.router.navigate([`../${currentPath}`, surveyId], {
      relativeTo: this.route,
    });
  }
}
