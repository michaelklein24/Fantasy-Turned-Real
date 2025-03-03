import { Component } from '@angular/core';
import { SurveyTableComponent } from '../../components/survey-table/survey-table.component';

@Component({
  selector: 'app-league-survey-page',
  standalone: true,
  imports: [
    SurveyTableComponent
  ],
  templateUrl: './league-survey-page.component.html',
  styleUrl: './league-survey-page.component.css'
})
export class LeagueSurveyPageComponent {
  
}
