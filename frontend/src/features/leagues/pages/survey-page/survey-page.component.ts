import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SurveyService } from '../../services/survey.service';
import { Survey } from '../../../../libs/generated/typescript-angular';
import { CommonModule } from '@angular/common';
import { SurveyDetailsPanelComponent } from '../../components/survey-details-panel/survey-details-panel.component';
import { getDefaultSurvey } from '../../../../shared/constants/survey-default';
import { QuestionsPanelComponent } from '../../components/questions-panel/questions-panel.component';

@Component({
  selector: 'app-survey-page',
  templateUrl: './survey-page.component.html',
  standalone: true,
  styleUrls: ['./survey-page.component.css'],
  imports: [CommonModule, SurveyDetailsPanelComponent, QuestionsPanelComponent],
})
export class SurveyPageComponent implements OnInit {
  survey: Survey = getDefaultSurvey();

  constructor(
    private route: ActivatedRoute,
    private surveyService: SurveyService
  ) {}

  ngOnInit(): void {
    const surveyId = this.route.snapshot.paramMap.get('surveyId');
    if (surveyId) {
      this.surveyService.getSurveyById(surveyId).subscribe((survey: Survey) => {
        this.survey = survey;
      });
    }
  }
}
