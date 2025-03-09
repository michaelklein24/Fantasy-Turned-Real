import { Component, Input } from '@angular/core';
import { SurveyTableComponent } from '../../components/survey-table/survey-table.component';
import { ModalComponent } from '../../../../shared/components/modal/modal.component';
import { CreateSurveyFormComponent } from '../../../../forms/create-survey-form/create-survey-form.component';

@Component({
  selector: 'app-league-survey-page',
  standalone: true,
  imports: [SurveyTableComponent, ModalComponent, CreateSurveyFormComponent],
  templateUrl: './league-survey-page.component.html',
  styleUrl: './league-survey-page.component.css',
})
export class LeagueSurveyPageComponent {
  isCreateSurveyModalOpen: boolean = false;

  closeCreateSurveyModal() {
    this.isCreateSurveyModalOpen = false;
  }

  openCreateSurveyModal() {
    console.log("TEST")
    this.isCreateSurveyModalOpen = true;
  }
}
