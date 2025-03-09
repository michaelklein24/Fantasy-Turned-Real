import { Component, OnDestroy, OnInit } from '@angular/core';
import { DropdownComponent } from '../../shared/components/dropdown/dropdown.component';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Survey } from '../../libs/generated/typescript-angular';
import { SurveyService } from '../../features/leagues/services/survey.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-create-survey-form',
  standalone: true,
  imports: [DropdownComponent, FormsModule, CommonModule],
  templateUrl: './create-survey-form.component.html',
  styleUrl: './create-survey-form.component.css',
})
export class CreateSurveyFormComponent implements OnInit, OnDestroy {
  survey: Survey = {};
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
  }

  ngOnDestroy(): void {
    this.paramsSubscription?.unsubscribe();
  }

  onSubmit(form: NgForm): void {
    const { surveyName, startTime, endTime } = form.value;

    this.surveyService
      .createSurvey(
        this.leagueId!,
        surveyName,
        new Date(startTime),
        new Date(endTime)
      )
      .subscribe({
        next: (survey: Survey) => {
          const currentPath = this.route.snapshot.url
            .map((segment) => segment.path)
            .join('/');
          this.router.navigate([`../${currentPath}`, survey.id], {
            relativeTo: this.route,
          });
        },
        error: (err) => {
          console.log(err);
        },
      });
  }
}
