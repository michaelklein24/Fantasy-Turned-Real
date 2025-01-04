import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { SurveyEntryPartComponent } from './survey-entry-part/survey-entry-part.component';
import { SurveyStatusPartComponent } from './survey-status-part/survey-status-part.component';

@Component({
  selector: 'app-surveys',
  standalone: true,
  imports: [CommonModule, SurveyStatusPartComponent, SurveyEntryPartComponent],
  templateUrl: './surveys.component.html',
  styleUrl: './surveys.component.css'
})
export class SurveysComponent implements OnInit, OnDestroy {

  // public previousSurveys: SurveyModel[] = [];
  // public activeSurvey: SurveyModel;

  private surveySub: Subscription;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    console.log(this.route.snapshot.params);
    let userId: string | null | undefined = this.route.parent?.snapshot.paramMap.get('userId');
    let leagueId: string | null | undefined = this.route.parent?.snapshot.paramMap.get('leagueId');

    if (userId && leagueId) {
      // this.surveySub = this.surveyService.getSurveysForUserForLeague(userId, leagueId).subscribe({
        // next: (surveys: SurveyModel[]) => {
          // this.activeSurvey = surveys.filter(surveyModel => surveyModel.active)[0];
          // this.previousSurveys = surveys.filter(surveyModel => !surveyModel.active);
        // }
      // });
    } else {
      console.error("Unable to parse userId and leagueId from path.");
    }

  }
  ngOnDestroy(): void {

  }

}
