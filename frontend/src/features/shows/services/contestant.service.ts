import { Injectable } from '@angular/core';
import {
  Contestant,
  GetContestantsResponse,
  Season,
  Show,
} from '../../../libs/generated/typescript-angular';
import { catchError, map, Observable, take, tap, throwError } from 'rxjs';
import { ApiService } from '../../../services/api.service';

@Injectable({
  providedIn: 'root',
})
export class ContestantService {
  constructor(private apiService: ApiService) {}

  getContestantsForSeason(
    show: string,
    seasonSequence: number
  ): Observable<Contestant[]> {
    return this.apiService.contestant.getContestants(show, seasonSequence).pipe(
      take(1),
      map((response: GetContestantsResponse) => response.contestants!),
      catchError((error: any) => {
        console.error(
          'Error fetching leagues for user:',
          error.response?.data || error.message
        );
        return throwError(() => error);
      })
    );
  }
}
