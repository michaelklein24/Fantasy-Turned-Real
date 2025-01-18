import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AuthControllerService, Configuration, LeagueControllerService, NotificationControllerService } from '../../../libs/generated/typescript-angular';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  public auth: AuthControllerService;
  public league: LeagueControllerService;
  public notification: NotificationControllerService;

  constructor(private http: HttpClient, private cookieService: CookieService) {
    const config = this.getApiConfiguration();
    const basePath = this.getBaseUrl();

    this.auth = new AuthControllerService(this.http, basePath, config);
    this.league = new LeagueControllerService(this.http, basePath, config);
    this.notification = new NotificationControllerService(this.http, basePath, config);
  }

  /**
   * Create the configuration object for the API clients.
   */
  private getApiConfiguration(): Configuration {
    const token = this.cookieService.get('jwtToken');

    return new Configuration({
      basePath: this.getBaseUrl(),
      accessToken: token ? `Bearer ${token}` : undefined,
    });
  }

  private getBaseUrl(): string {
    return 'http://localhost:8080';
  }
}
