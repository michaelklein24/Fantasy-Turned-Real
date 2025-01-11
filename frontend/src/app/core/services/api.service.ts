import { Injectable } from '@angular/core';
import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios';
import { CookieService } from 'ngx-cookie-service';
import { AuthControllerApi, LeagueControllerApi } from '../../../libs/generated';
import { InternalAxiosRequestConfig } from 'axios';
import { AxiosHeaders } from 'axios';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private axiosInstance: AxiosInstance;
  public auth: AuthControllerApi;
  public league: LeagueControllerApi;

  constructor(private cookieService: CookieService) {
    this.axiosInstance = this.createAxiosInstance();
    this.auth = new AuthControllerApi(undefined, this.getBaseUrl(), this.axiosInstance);
    this.league = new LeagueControllerApi(undefined, this.getBaseUrl(), this.axiosInstance);
  }

  private createAxiosInstance(): AxiosInstance {
    const instance = axios.create({
      baseURL: this.getBaseUrl(),
      timeout: 10000,
      headers: {
        'Content-Type': 'application/json',
      },
    });

    this.initializeRequestInterceptors(instance);
    this.initializeResponseInterceptors(instance);

    return instance;
  }

  private getBaseUrl(): string {
    return 'http://localhost:8080';
  }

  private initializeRequestInterceptors(instance: AxiosInstance): void {
    instance.interceptors.request.use(
      (config: InternalAxiosRequestConfig) => {
        const token = this.cookieService.get('jwtToken');
        const excludedEndpoints: string[] = [
          '/auth/login',
          '/auth/register',
          '/auth/refresh-token',
        ];

        if (config.url && !excludedEndpoints.some((endpoint) => config.url!.includes(endpoint))) {
          if (token) {
            if (!config.headers) {
              config.headers = new AxiosHeaders();
            }
            config.headers.set('Authorization', `Bearer ${token}`);
          }
        }
        

        return config;
      },
      (error: any) => Promise.reject(error)
    );
  }

  private initializeResponseInterceptors(instance: AxiosInstance): void {
    instance.interceptors.response.use(
      (response: AxiosResponse) => response,
      (error: any) => {
        const standardizedError = {
          message: error.response?.data?.message || 'An unexpected error occurred.',
          status: error.response?.status || 500,
        };
        return Promise.reject(standardizedError);
      }
    );
  }
}
