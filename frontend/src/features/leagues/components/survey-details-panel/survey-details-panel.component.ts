import { Component, Input } from '@angular/core';
import { Survey } from '../../../../libs/generated/typescript-angular';
import { CommonModule } from '@angular/common';
import { getDefaultSurvey } from '../../../../shared/constants/survey-default';

@Component({
  selector: 'app-survey-details-panel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './survey-details-panel.component.html',
  styleUrl: './survey-details-panel.component.css',
})
export class SurveyDetailsPanelComponent {
  @Input({ required: true }) survey: Survey = getDefaultSurvey();
}
