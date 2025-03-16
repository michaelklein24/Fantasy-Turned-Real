import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SurveyService } from '../../services/survey.service';
import {
  Question,
  Survey,
} from '../../../../libs/generated/typescript-angular';
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
        this.survey.questions = [
          {
            sequence: '1',
            text: 'Who will be voted out?',
            type: Question.TypeEnum.MultipleChoice,
            points: 100,
            answerOptions: [
              {
                sequence: '1',
                questionSequence: '1',
                surveyId: 'survey123',
                text: 'Contestant A',
              },
              {
                sequence: '2',
                questionSequence: '1',
                surveyId: 'survey123',
                text: 'Contestant B',
              },
              {
                sequence: '3',
                questionSequence: '1',
                surveyId: 'survey123',
                text: 'Contestant C',
              },
            ],
            participantAnswers: [
              { questionSequence: '1', surveyId: 'survey123' },
            ],
            correctAnswer: {
              sequence: '1',
              questionSequence: '1',
              surveyId: 'survey123',
              text: 'Contestant A',
            },
          },
          {
            sequence: '2',
            text: 'Will there be a blindside?',
            type: Question.TypeEnum.TrueFalse,
            points: 25,
            answerOptions: [
              {
                sequence: '1',
                questionSequence: '2',
                surveyId: 'survey123',
                text: 'True',
              },
              {
                sequence: '2',
                questionSequence: '2',
                surveyId: 'survey123',
                text: 'False',
              },
            ],
            participantAnswers: [
              { questionSequence: '2', surveyId: 'survey123' },
            ],
            correctAnswer: {
              sequence: '1',
              questionSequence: '2',
              surveyId: 'survey123',
              text: 'Yes',
            },
          },
          {
            sequence: '3',
            text: 'Which tribe will win a challenge?',
            type: Question.TypeEnum.SingleChoice,
            points: 100,
            answerOptions: [
              {
                sequence: '1',
                questionSequence: '3',
                surveyId: 'survey123',
                text: 'Tribe X',
              },
              {
                sequence: '2',
                questionSequence: '3',
                surveyId: 'survey123',
                text: 'Tribe Y',
              },
            ],
            participantAnswers: [
              { questionSequence: '3', surveyId: 'survey123' },
            ],
            correctAnswer: {
              sequence: '1',
              questionSequence: '3',
              surveyId: 'survey123',
              text: 'Tribe X',
            },
          },
          {
            sequence: '4',
            text: 'Which tribe will win a challenge?',
            type: 'SINGLE_CHOICE',
            points: 100,
            answerOptions: [
              {
                sequence: '1',
                questionSequence: '3',
                surveyId: 'survey123',
                text: 'Red Tribe',
              },
              {
                sequence: '2',
                questionSequence: '3',
                surveyId: 'survey123',
                text: 'Blue Tribe',
              },
            ],
            participantAnswers: [
              { questionSequence: '4', surveyId: 'survey123' },
            ],
            correctAnswer: {
              sequence: '2',
              questionSequence: '3',
              surveyId: 'survey123',
              text: 'Blue Tribe',
            },
          },
        ];
      });
    }
  }
}
