import { Injectable } from '@angular/core';
import { catchError, map, take, tap } from 'rxjs/operators';
import { LoginUserRequest, LoginUserResponse, RegisterUserRequest, RegisterUserResponse } from '../../../../libs/generated/typescript-angular';
import { ApiService } from '../../../core/services/api.service';
import { SessionService } from './session.service';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    private apiService: ApiService,
    private sessionService: SessionService
  ) {}

  registerUser(firstName: string, lastName: string, email: string, password: string): Observable<void> {
    const request: RegisterUserRequest = { firstName, lastName, email, password };

    return this.apiService.auth.register(request).pipe(
      take(1),
      tap((response: RegisterUserResponse) => {
        this.sessionService.startSession(response.accessToken!);
      }),
      map((response: RegisterUserResponse) => {}),
      catchError((error: any) => {
        console.error('Registration failed:', error);
        throw error;
      })
    );
  }

  loginUser(email: string, password: string): Observable<void> {
    const request: LoginUserRequest = { email, password };

    return this.apiService.auth.login(request).pipe(
      take(1), // Unsubscribe automatically after 1 emission
      tap((response: LoginUserResponse) => {
        // Extract token and start session on successful login
        const token = response.accessToken;
        this.sessionService.startSession(response.accessToken!); // Start session with token
      }),
      map((response: LoginUserResponse) => {}),
      catchError((error: any) => {
        console.error('Login failed:', error);
        throw error;
      })
    );
  }
}
