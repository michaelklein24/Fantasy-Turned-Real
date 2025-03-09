import {
  Component,
  OnInit,
  OnDestroy,
  Output,
  EventEmitter,
} from '@angular/core';
import { SurveyService } from '../../services/survey.service';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { SurveyStatusBadgeComponent } from '../survey-status-badge/survey-status-badge.component';
import { Survey } from '../../../../libs/generated/typescript-angular';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-survey-table',
  standalone: true,
  imports: [SurveyStatusBadgeComponent, CommonModule],
  templateUrl: './survey-table.component.html',
  styleUrl: './survey-table.component.css',
})
export class SurveyTableComponent implements OnInit, OnDestroy {
  surveys: Survey[] = [];
  leagueId: string | undefined;
  sortColumn: string = '';
  sortDirection: boolean = true; // true for ascending, false for descending

  @Output() openCreateSurveyForm: EventEmitter<void> = new EventEmitter();

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

    this.surveyService
      .getSurveysForLeague(this.leagueId!)
      .subscribe((surveys: Survey[]) => {
        this.surveys = surveys;
      });
  }

  ngOnDestroy(): void {
    this.paramsSubscription?.unsubscribe();
  }

  handleSurveyClick(surveyId: string): void {
    const currentPath = this.route.snapshot.url
      .map((segment) => segment.path)
      .join('/');
    this.router.navigate([`../${currentPath}`, surveyId], {
      relativeTo: this.route,
    });
  }

  handleOpenCreateSurveyForm() {
    this.openCreateSurveyForm.emit();
  }

  sortTable(column: string): void {
    if (this.sortColumn === column) {
      this.sortDirection = !this.sortDirection;
    } else {
      this.sortColumn = column;
      this.sortDirection = true;
    }

    this.surveys.sort((a: Survey, b: Survey) => {
      const valueA = a[column as keyof Survey];
      const valueB = b[column as keyof Survey];

      if (valueA! < valueB!) return this.sortDirection ? -1 : 1;
      if (valueA! > valueB!) return this.sortDirection ? 1 : -1;
      return 0;
    });
  }
}
