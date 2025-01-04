import { Component } from '@angular/core';
import { SurveyResultsPartComponent } from '../survey-results-part/survey-results-part.component';
import { SelectControlComponent } from './select-control/select-control.component';

@Component({
  selector: 'app-survey',
  standalone: true,
  imports: [SurveyResultsPartComponent, SelectControlComponent],
  templateUrl: './survey.component.html',
  styleUrl: './survey.component.css'
})
export class SurveyComponent {

}
