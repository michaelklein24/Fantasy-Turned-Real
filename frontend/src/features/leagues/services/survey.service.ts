import { Injectable } from '@angular/core';
import { ApiService } from '../../../services/api.service';
import { catchError, map, Observable, take, tap, throwError } from 'rxjs';
import {
  CreateSurveyRequest,
  CreateSurveyResponse,
  GetSurveyByIdResponse,
  GetSurveysResponse,
  Survey,
  UpdateSurveyRequest,
} from '../../../libs/generated/typescript-angular';

@Injectable({
  providedIn: 'root',
})
export class SurveyService {
  constructor(private apiService: ApiService) {}

  public getSurveysForLeague(leagueId: string): Observable<Survey[]> {
    return this.apiService.survey.getSurveys(leagueId).pipe(
      take(1),
      map((response: GetSurveysResponse) => response.surveys!),
      catchError((error: any) => {
        console.error(
          'Error fetching surveys:',
          error.response?.data || error.message
        );
        return throwError(() => error);
      })
    );
  }

  public getSurveyById(surveyId: string): Observable<Survey> {
    return this.apiService.survey.getSurveyById(surveyId).pipe(
      take(1),
      map((response: GetSurveyByIdResponse) => response.survey!),
      catchError((error: any) => {
        console.error(
          'Error fetching survey by ID:',
          error.response?.data || error.message
        );
        return throwError(() => error);
      })
    );
  }

  public createSurvey(
    leagueId: string,
    name: string,
    startDate: Date,
    endDate: Date
  ): Observable<Survey> {
    const start: string = startDate.toISOString().split('T')[0];
    const end: string = endDate.toISOString().split('T')[0];

    const request: CreateSurveyRequest = {
      leagueId: leagueId,
      name: name,
      startDate: start,
      endDate: end,
    };

    return this.apiService.survey.createSurvey(request).pipe(
      take(1),
      map((response: CreateSurveyResponse) => response.survey!),
      catchError((error: any) => {
        console.error(
          'Error creating survey:',
          error.response?.data || error.message
        );
        return throwError(() => error);
      })
    );
  }

  public updateSurveyDetails(
    surveyId: string,
    name: string,
    startDate: Date,
    endDate: Date
  ): Observable<void> {
    const start: string = startDate.toISOString().split('.')[0]; 
    const end: string = endDate.toISOString().split('.')[0];
  
    const request: UpdateSurveyRequest = {
      name: name,
      startDate: start,
      endDate: end,
    };
  
    console.log(request);
  
    return this.apiService.survey.updateSurvey(surveyId, request).pipe(
      take(1),
      catchError((error: any) => {
        console.error(
          'Error updating survey:',
          error.response?.data || error.message
        );
        return throwError(() => error);
      })
    );
  }
  

  public deleteSurvey(surveyId: string): Observable<void> {
    return this.apiService.survey.deleteSurvey(surveyId).pipe(
      catchError((error: any) => {
        console.error(
          `Error deleting survey with ID ${surveyId}:`,
          error.response?.data || error.message
        );
        return throwError(() => error);
      })
    );
  }
}
