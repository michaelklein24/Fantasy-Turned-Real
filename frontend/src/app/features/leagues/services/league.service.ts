import { Injectable } from '@angular/core';
import { AxiosResponse } from 'axios';
import { CreateLeagueRequest, CreateLeagueResponse, GetInvitesForLeagueResponse, GetLeaguesForUserResponse, InviteUserToLeagueResponse, Show } from '../../../../libs/generated';
import { ApiService } from '../../../core/services/api.service';
import { TokenService } from '../../auth/services/token.service';

@Injectable({
  providedIn: 'root',
})
export class LeagueService {
  constructor(private tokenService: TokenService, private apiService: ApiService) {}

  async createLeague(
    name: string,
    seasonNumber: number
  ): Promise<AxiosResponse<CreateLeagueResponse>> {
    const request: CreateLeagueRequest = {
      name: name,
      show: Show.Survivor,
      seasonSequence: seasonNumber
    };
    console.log(request)
    return await this.apiService.league.createLeague(request);
  }

  async getLeaguesForUser(): Promise<AxiosResponse<GetLeaguesForUserResponse>> {
    return await this.apiService.league.getLeaguesForUser();
  }

  async getInvitesForLeague(leagueId: string): Promise<AxiosResponse<GetInvitesForLeagueResponse>> {
    return await this.apiService.league.getInvitesForLeague(leagueId)
  }

  async inviteUserToLeague(leagueId: string, inviteeEmail: string): Promise<AxiosResponse<InviteUserToLeagueResponse>> {
    return await this.apiService.league.inviteUserToLeague(leagueId, {
      inviteeEmail: inviteeEmail
    })
  }
}
