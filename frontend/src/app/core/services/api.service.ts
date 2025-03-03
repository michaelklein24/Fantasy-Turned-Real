import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { AuthControllerService, Configuration, ContestantControllerService, LeagueControllerService, NotificationControllerService, ShowControllerService, SurveyControllerService } from '../../../libs/generated/typescript-angular';
import { catchError, Observable, take, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  public auth: AuthControllerService;
  public league: LeagueControllerService;
  public notification: NotificationControllerService;
  public contestant: ContestantControllerService;
  public show: ShowControllerService;
  public survey: SurveyControllerService;


  constructor(private http: HttpClient, private cookieService: CookieService) {
    const config = this.getApiConfiguration();
    const basePath = this.getBaseUrl();

    this.auth = new AuthControllerService(this.http, basePath, config);
    this.league = new LeagueControllerService(this.http, basePath, config);
    this.notification = new NotificationControllerService(this.http, basePath, config);
    this.contestant = new ContestantControllerService(this.http, basePath, config);
    this.show = new ShowControllerService(this.http, basePath, config);
    this.survey = new SurveyControllerService(this.http, basePath, config);
  }

  /**
   * Create the configuration object for the API clients.
   */
  private getApiConfiguration(): Configuration {
    return new Configuration({
      basePath: this.getBaseUrl(),
      accessToken: this.getAuthorizationHeader(),
    });
  }

  private getAuthorizationHeader(): string {
    const token = this.cookieService.get('jwtToken');
    return token ? `Bearer ${token}` : '';
  
  }

  private getBaseUrl(): string {
    return 'http://localhost:8080';
  }

  performAction(endpoint: string, requestBody: any, method: 'POST' | 'PUT' = 'POST'): Observable<any> {
    const options = { 
      headers: { 
        Authorization: this.getAuthorizationHeader(),
        'Content-Type': 'application/json'
      } 
    };
    const url = this.getBaseUrl() + endpoint;
    let request: Observable<any>;
    if (method === 'POST') {
      request = this.http.post(url, requestBody, options);
    } else if (method === 'PUT') {
      request = this.http.put(url, requestBody, options);
    } else {
      throw new Error(`Unsupported HTTP method: ${method}`);
    }
    return request.pipe(
      take(1),
      catchError((error: any) => {
        console.error('Unable to perform action:', error.response?.data || error.message);
        return throwError(() => error);
      })
    )
  }
  
}
