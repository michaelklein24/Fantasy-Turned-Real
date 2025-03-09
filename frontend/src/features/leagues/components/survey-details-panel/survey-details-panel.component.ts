import { Component, Input } from '@angular/core';
import { Survey } from '../../../../libs/generated/typescript-angular';
import { CommonModule } from '@angular/common';
import { getDefaultSurvey } from '../../../../shared/constants/survey-default';
import { FormsModule } from '@angular/forms';
import { DropdownComponent } from '../../../../shared/components/dropdown/dropdown.component';
import { SurveyService } from '../../services/survey.service';
import { SurveyStatusBadgeComponent } from "../survey-status-badge/survey-status-badge.component";

@Component({
  selector: 'app-survey-details-panel',
  standalone: true,
  imports: [CommonModule, FormsModule, SurveyStatusBadgeComponent],
  templateUrl: './survey-details-panel.component.html',
  styleUrl: './survey-details-panel.component.css',
})
export class SurveyDetailsPanelComponent {
  @Input({ required: true }) survey: Survey = getDefaultSurvey();
  isEditing = false;

  constructor(private surveyService: SurveyService) {}

  enterEditMode() {
    this.isEditing = true;
  }

  saveChanges() {
    const startTime = new Date(this.survey.startTime!) ;
    const endTime = new Date(this.survey.endTime!);
  
    console.log(this.survey);
    this.surveyService
      .updateSurveyDetails(
        this.survey.id!,
        this.survey.name!,
        startTime,
        endTime
      )
      .subscribe();
    this.isEditing = false;
  }

  cancelEdit() {
    this.isEditing = false;
  }
}
