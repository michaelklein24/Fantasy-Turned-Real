import { Injectable } from '@angular/core';
import { AxiosResponse } from 'axios';
import { LoginUserRequest, LoginUserResponse, RegisterUserRequest, RegisterUserResponse } from '../../../../libs/generated';
import { ApiService } from '../../../core/services/api.service';
import { SessionService } from './session.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private apiService : ApiService,
    private sessionService: SessionService
  ) { }

  async registerUser(firstName: string, lastName: string, email: string, password: string): Promise<void> {
    const request: RegisterUserRequest = { firstName, lastName, email, password };

    try {
      const response: AxiosResponse<RegisterUserResponse> =
        await this.apiService.auth.register(request);

      const token: string = response.data.accessToken!;
      
      this.sessionService.startSession(token);
    } catch (error: any) {
      console.error('Error during registration:', error);
      throw error;
    }
  }

  async loginUser(email: string, password: string): Promise<void> {
    const request: LoginUserRequest = { email, password };

    try {
      const response: AxiosResponse<LoginUserResponse> =
        await this.apiService.auth.login(request);
      const accessToken = response.data.accessToken!;
      
      this.sessionService.startSession(accessToken);
    } catch (error: any) {
      console.error('Error during login:', error);
      throw error;
    }
  }
}
