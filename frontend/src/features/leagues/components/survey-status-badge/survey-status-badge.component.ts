import { Component, Input } from '@angular/core';
import { SurveyStatus } from '../../../../libs/generated/typescript-angular';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-survey-status-badge',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './survey-status-badge.component.html',
  styleUrl: './survey-status-badge.component.css',
})
export class SurveyStatusBadgeComponent {
  @Input({ required: true }) status!: SurveyStatus;
}
