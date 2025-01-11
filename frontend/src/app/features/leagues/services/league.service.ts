import { Injectable } from '@angular/core';
import { AxiosResponse } from 'axios';
import { 
  CreateLeagueRequest, 
  CreateLeagueResponse, 
  GetInvitesForLeagueResponse, 
  GetLeaguesForUserResponse, 
  Invite, 
  InviteUserToLeagueResponse, 
  League, 
  Show 
} from '../../../../libs/generated';
import { ApiService } from '../../../core/services/api.service';

@Injectable({
  providedIn: 'root',
})
export class LeagueService {
  private cacheTTL = 5 * 60 * 1000; // 5 minutes
  private leaguesCacheTimestamp = 0; // Timestamp for leagues cache
  private invitesCache: { [leagueId: string]: GetInvitesForLeagueResponse } = {};
  private invitesCacheTimestamp: { [leagueId: string]: number } = {}; // Timestamp for invites cache
  
  leagues: League[] = [];
  userSelectedLeague: League | undefined = undefined;
  declinedLeagueInvites: Invite[] = [];
  approvedLeagueInvites: Invite[] = [];
  pendingLeagueInvites: Invite[] = [];

  constructor(private apiService: ApiService) {}

  async createLeague(
    name: string,
    seasonNumber: number
  ): Promise<CreateLeagueResponse> {
    const request: CreateLeagueRequest = {
      name,
      show: Show.Survivor,
      seasonSequence: seasonNumber,
    };
  
    try {
      const response: AxiosResponse<CreateLeagueResponse> = await this.apiService.league.createLeague(request);
  
      // Add the newly created league to the leagues array
      const newLeague = response.data.league!;
      this.leagues.unshift(newLeague);
  
      return response.data;
    } catch (error: any) {
      console.error('Error creating league:', error.response?.data || error.message);
      throw error;
    }
  }
  

  async getLeaguesForUser(forceRefresh = false): Promise<League[]> {
    const now = Date.now();
    if (!forceRefresh && this.leagues.length && now - this.leaguesCacheTimestamp < this.cacheTTL) {
      return this.leagues;
    }

    try {
      const response: AxiosResponse<GetLeaguesForUserResponse> = await this.apiService.league.getLeaguesForUser();
      this.leagues = response.data.leagues!;
      this.leaguesCacheTimestamp = now;
      return this.leagues!;
    } catch (error: any) {
      console.error('Error fetching leagues for user:', error.response?.data || error.message);
      throw error;
    }
  }

  async getInvitesForLeague(leagueId: string, forceRefresh = false): Promise<GetInvitesForLeagueResponse> {
    const now = Date.now();
  
    // Check if the invites are cached and if cache is still valid
    if (!forceRefresh && this.invitesCache[leagueId] && now - this.invitesCacheTimestamp[leagueId] < this.cacheTTL) {
      console.log('Returning cached invites for league:', leagueId);
      const cachedInvitesData = this.invitesCache[leagueId];
      this.pendingLeagueInvites = cachedInvitesData?.pending || [];
      this.approvedLeagueInvites = cachedInvitesData?.approved || [];
      this.declinedLeagueInvites = cachedInvitesData?.declined || [];
      return cachedInvitesData;
    }
  
    try {
      const response: AxiosResponse<GetInvitesForLeagueResponse> = await this.apiService.league.getInvitesForLeague(leagueId);
      const { pending, approved, declined } = response.data;
  
      // Store the fetched data in the cache with a timestamp
      this.invitesCache[leagueId] = response.data;
      this.invitesCacheTimestamp[leagueId] = now;
  
      // Store invites in separate arrays for UI purposes
      this.pendingLeagueInvites = pending!;
      this.approvedLeagueInvites = approved!;
      this.declinedLeagueInvites = declined!;
  
      return response.data;
    } catch (error: any) {
      console.error('Error fetching invites for league:', error.response?.data || error.message);
      throw error;
    }
  }

  async inviteUserToLeague(leagueId: string, inviteeEmail: string): Promise<Invite> {
    const now = Date.now();
    
    try {
      const response: AxiosResponse<InviteUserToLeagueResponse> = await this.apiService.league.inviteUserToLeague(leagueId, {
        inviteeEmail,
      });
      const newInvite = response.data.invite!;
  
      // Update pending invites
      this.pendingLeagueInvites.push(newInvite);
  
      // Update cache
      if (this.invitesCache[leagueId]) {
        // If the league already has cached invites, update the pending invites array
        this.invitesCache[leagueId].pending?.push(newInvite)
      } else {
        // If no cache exists for this league, create a new cache entry
        this.invitesCache[leagueId] = {
          pending: [newInvite],
          approved: [],
          declined: [],
        };
      }
      
      // Update the cache timestamp for the league
      this.invitesCacheTimestamp[leagueId] = now;
  
      return newInvite;
    } catch (error: any) {
      console.error('Error inviting user to league:', error.response?.data || error.message);
      throw error;
    }
  }

  clearCache(): void {
    this.leagues = [];
    this.pendingLeagueInvites = [];
    this.approvedLeagueInvites = [];
    this.declinedLeagueInvites = [];
    this.userSelectedLeague = undefined;
    this.leaguesCacheTimestamp = 0; // Reset cache timestamp
    this.invitesCacheTimestamp = {}; // Reset cache timestamp
  }
}
