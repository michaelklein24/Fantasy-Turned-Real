import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, take, tap, throwError } from 'rxjs';
import { 
  CreateLeagueRequest, 
  CreateLeagueResponse, 
  GetInvitesForLeagueResponse, 
  GetLeagueByIdResponse, 
  GetLeaguesForUserResponse, 
  Invite, 
  InviteUserToLeagueResponse, 
  League, 
  Show 
} from '../../../../libs/generated/typescript-angular';
import { ApiService } from '../../../core/services/api.service';
import { CacheService } from '../../../core/services/cache.service';

@Injectable({
  providedIn: 'root',
})
export class LeagueService {
  private readonly cacheTTL = 5 * 60 * 1000; // 5 minutes

  leagues: League[] = [];
  userSelectedLeague: League | undefined = undefined;

  constructor(private apiService: ApiService, private cacheService: CacheService) {}

  createLeague(name: string, show: string, seasonNumber: number): Observable<CreateLeagueResponse> {
    const request: CreateLeagueRequest = {
      name,
      show: show,
      seasonSequence: seasonNumber,
    };

    return this.apiService.league.createLeague(request).pipe(
      take(1),
      tap((response: CreateLeagueResponse) => {
        const newLeague = response.league;
        if (newLeague) {
          const leagues = this.cacheService.get<League[]>('leagues') || [];
          leagues.unshift(newLeague);
          this.cacheService.set('leagues', leagues, this.cacheTTL);
        }
      }),
      catchError((error: any) => {
        console.error('Error creating league:', error.response?.data || error.message);
        return throwError(() => error);
      })
    );
  }

  getLeaguesForUser(forceRefresh = false): Observable<League[]> {
    if (!forceRefresh) {
      const cachedLeagues = this.cacheService.get<League[]>('leagues');
      if (cachedLeagues) {
        return of(cachedLeagues);
      }
    }

    return this.apiService.league.getLeaguesForUser().pipe(
      take(1),
      tap((response: GetLeaguesForUserResponse) => {
        this.cacheService.set('leagues', response.leagues || [], this.cacheTTL);
      }),
      map((response: GetLeaguesForUserResponse) => response.leagues!),
      catchError((error: any) => {
        console.error('Error fetching leagues for user:', error.response?.data || error.message);
        return throwError(() => error);
      })
    );
  }

  getLeagueById(leagueId: string, forceRefresh = false): Observable<League> {
    if (!forceRefresh) {
      const cachedLeagues = this.cacheService.get<League[]>('leagues');
      if (cachedLeagues) {
        const cachedLeague = cachedLeagues.find((league) => league.leagueId! === leagueId);
        if (cachedLeague) {
          return of(cachedLeague); // Return the league if it exists in the cached list
        }
      }
    }
  
    // Fetch the league by ID from the API if not cached or forceRefresh is true
    return this.apiService.league.getLeagueById(leagueId).pipe(
      take(1),
      map((response: GetLeagueByIdResponse) => response.league!),
      tap((fetchedLeague: League) => {
        const cachedLeagues = this.cacheService.get<League[]>('leagues') || [];
        const updatedLeagues = [
          ...cachedLeagues.filter((league) => league.leagueId! !== leagueId), // Remove any existing entry for this leagueId
          fetchedLeague, // Add or update the fetched league
        ];
        this.cacheService.set('leagues', updatedLeagues, this.cacheTTL); // Update the unified cache
      }),
      catchError((error: any) => {
        console.error(`Error fetching league by ID (${leagueId}):`, error.response?.data || error.message);
        return throwError(() => error);
      })
    );
  }

  getInvitesForLeague(leagueId: string, forceRefresh = false): Observable<Invite[]> {
    if (!forceRefresh) {
      const cachedInvites = this.cacheService.get<Invite[]>(`invites_${leagueId}`);
      if (cachedInvites) {
        return of(cachedInvites);
      }
    }

    return this.apiService.league.getInvitesForLeague(leagueId).pipe(
      take(1),
      tap((response: GetInvitesForLeagueResponse) => {
        this.cacheService.set(`invites_${leagueId}`, response.invites || [], this.cacheTTL);
      }),
      map((response: GetInvitesForLeagueResponse) => response.invites!),
      catchError((error: any) => {
        console.error('Error fetching invites for league:', error.response?.data || error.message);
        return throwError(() => error);
      })
    );
  }

  inviteUserToLeague(leagueId: string, inviteeEmail: string): Observable<Invite> {
    return this.apiService.league.inviteUserToLeague(leagueId, { inviteeEmail }).pipe(
      take(1),
      tap((response: InviteUserToLeagueResponse) => {
        const newInvite = response.invite!;
        const invites = this.cacheService.get<Invite[]>(`invites_${leagueId}`) || [];
        invites.push(newInvite);
        this.cacheService.set(`invites_${leagueId}`, invites, this.cacheTTL);
      }),
      map((response: InviteUserToLeagueResponse) => response.invite!),
      catchError((error: any) => {
        console.error('Error inviting user to league:', error.response?.data || error.message);
        return throwError(() => error);
      })
    );
  }

  clearCache(): void {
    this.cacheService.clear();
  }
}
