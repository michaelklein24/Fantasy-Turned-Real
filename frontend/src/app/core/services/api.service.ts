import { Injectable } from '@angular/core';
import axios, { AxiosInstance } from 'axios';
import { CookieService } from 'ngx-cookie-service';
import { AuthControllerApi, LeagueControllerApi } from '../../../libs/generated';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private axiosInstance: AxiosInstance;
  public auth: AuthControllerApi;
  public league: LeagueControllerApi;

  constructor(private cookieService: CookieService) {
    // Create axios instance
    this.axiosInstance = axios.create({
      baseURL: 'http://localhost:8080',
      timeout: 10000,
      headers: {
        'Content-Type': 'application/json',
      },
    });

    this.initializeInterceptors();

    this.auth = new AuthControllerApi(undefined, "http://localhost:8080", this.axiosInstance);
    this.league = new LeagueControllerApi(undefined, "http://localhost:8080", this.axiosInstance);
  }

  private initializeInterceptors() {
    this.axiosInstance.interceptors.request.use(
      (config: any) => {
        const token = this.cookieService.get('jwtToken');

        // Define endpoints to exclude from adding Authorization header
        const excludedEndpoints : string[] = [
          '/auth/login', 
          '/auth/register', 
          '/auth/refresh-token'
        ];

        // Skip adding Authorization header if the URL matches any excluded endpoint
        if (config.url && excludedEndpoints.some(endpoint => config.url.includes(endpoint))) {
          return config;
        }

        // Add Authorization header if token exists
        if (token) {
          config.headers!['Authorization'] = `Bearer ${token}`;
        }

        return config;
      },
      (error: Error) => Promise.reject(error)
    );
  }
}

export * from '../../../libs/generated';
