import { Injectable } from '@angular/core';
import { CreateLeagueRequest, CreateLeagueResponse } from '../shared/generated';
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
    name: string
  ): Promise<AxiosResponse<CreateLeagueResponse>> {
    const request: CreateLeagueRequest = {
      name: name,
    };
    const token = this.tokenService.getToken();
    // Pass the headers into the options object
    const requestOptions: AxiosRequestConfig = {
      
    };
    return await this.apiService.league.createLeague(request, requestOptions);
  }
}
