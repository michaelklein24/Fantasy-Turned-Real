import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Survey } from '../../../../libs/generated/typescript-angular';
import { CommonModule } from '@angular/common';
import { getDefaultSurvey } from '../../../../shared/constants/survey-default';
import { FormsModule } from '@angular/forms';
import { SurveyService } from '../../services/survey.service';
import { SurveyStatusBadgeComponent } from '../survey-status-badge/survey-status-badge.component';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ToastService } from '../../../../services/toast.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-survey-details-panel',
  standalone: true,
  imports: [CommonModule, FormsModule, SurveyStatusBadgeComponent],
  templateUrl: './survey-details-panel.component.html',
  styleUrl: './survey-details-panel.component.css',
})
export class SurveyDetailsPanelComponent implements OnInit, OnDestroy {
  @Input({ required: true }) survey: Survey = getDefaultSurvey();
  isEditing = false;

  paramsSubscription: Subscription | undefined;
  leagueId: string | undefined;

  constructor(
    private surveyService: SurveyService,
    private router: Router,
    private route: ActivatedRoute,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.paramsSubscription = this.route.parent!.params.subscribe(
      (params: Params) => {
        this.leagueId = params['leagueId'];
      }
    );
  }

  ngOnDestroy(): void {}

  enterEditMode() {
    this.isEditing = true;
  }

  saveChanges() {
    const startTime = new Date(this.survey.startTime!);
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

  deleteSurvey() {
    this.surveyService.deleteSurvey(this.survey.id!).subscribe({
      next: () => {
        this.toastService.toastSuccess('Survey was successfully deleted', 3000);
        this.router.navigate(['dashboard', 'league', this.leagueId!, 'survey']);
      },
      error: (err: Error) => {
        this.toastService.toastApiError('delete survey', err, 5);
      },
    });
  }
}
