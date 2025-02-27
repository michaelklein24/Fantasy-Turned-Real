import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, take, tap, throwError } from 'rxjs';
import { GetSeasonsResponse, GetShowsResponse, Season, Show } from '../../../../libs/generated/typescript-angular';
import { ApiService } from '../../../core/services/api.service';
import { CacheService } from '../../../core/services/cache.service';

@Injectable({
  providedIn: 'root'
})
export class ShowService {

  constructor(
    private apiService: ApiService,
    private cacheService: CacheService
  ) { }

  private readonly cacheTTL = 5 * 60 * 1000; // 5 minutes

  getSeasonsForShow(show: Show, forceRefresh = false): Observable<Season[]> {
    if (!forceRefresh) {
      const cachedSeasons = this.cacheService.get<Season[]>(`${show.name!.toLowerCase()}_seasons`);
      if (cachedSeasons) {
        return of(cachedSeasons);
      }
    }

    return this.apiService.show.getSeasons(show.name!).pipe(
      take(1),
      map((response: GetSeasonsResponse) => response.seasons!),
      tap((seasons: Season[]) => {
        this.cacheService.set(`${show.name!.toLowerCase()}_seasons`, seasons || []);
      }),
      catchError((error: any) => {
        console.error('Error fetching leagues for user:', error.response?.data || error.message);
        return throwError(() => error);
      })
    )
  }

  getShows(forceRefresh = false): Observable<Show[]> {
    if (!forceRefresh) {
      const cachedShows = this.cacheService.get<[]>(`shows`);
      if (cachedShows) {
        return of(cachedShows);
      }
    }

    return this.apiService.show.getShows().pipe(
      take(1),
      map((response: GetShowsResponse) => response.shows!),
      tap((shows: Show[]) => {
        this.cacheService.set(`shows`, shows || []);
      }),
      catchError((error: any) => {
        console.error('Error fetching leagues for user:', error.response?.data || error.message);
        return throwError(() => error);
      })
    )
  }
}
