import { CommonModule } from '@angular/common';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SurveyResultsPartComponent } from '../survey-results-part/survey-results-part.component';

@Component({
  selector: 'app-survey-entry-part',
  standalone: true,
  imports: [CommonModule, RouterModule, SurveyResultsPartComponent],
  templateUrl: './survey-entry-part.component.html',
  styleUrl: './survey-entry-part.component.css'
})
export class SurveyEntryPartComponent {
  @Input() surveyId: string;
}
