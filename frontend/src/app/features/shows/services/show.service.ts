import { Injectable } from '@angular/core';
import { catchError, map, Observable, take, throwError } from 'rxjs';
import { GetSeasonsResponse, Season, Show } from '../../../../libs/generated/typescript-angular';
import { ApiService } from '../../../core/services/api.service';

@Injectable({
  providedIn: 'root'
})
export class ShowService {

  constructor(
    private apiService: ApiService
  ) { }

  getSeasonsForShow(show: Show): Observable<Season[]> {
    return this.apiService.show.getSeasons(show).pipe(
      take(1),
      map((response: GetSeasonsResponse) => response.seasons!),
      catchError((error: any) => {
        console.error('Error fetching leagues for user:', error.response?.data || error.message);
        return throwError(() => error);
      })
    )
  }
}
