import { Injectable } from '@angular/core';
import { CreateLeagueRequest, CreateLeagueResponse, GetLeaguesForUserResponse, Show } from '../shared/generated';
import { AxiosResponse } from 'axios';
import { AxiosRequestConfig } from 'axios';
import { TokenService } from './token.service';
import { ApiService } from './api.service';

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
      show: Show.SURVIVOR,
      seasonSequence: seasonNumber
    };
    console.log(request)
    return await this.apiService.league.createLeague(request);
  }

  async getLeaguesForUser(): Promise<AxiosResponse<GetLeaguesForUserResponse>> {
    return await this.apiService.league.getLeaguesForUser();
  }
}
