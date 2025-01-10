import { Injectable } from '@angular/core';
import { AxiosResponse } from 'axios';
import { LoginUserRequest, LoginUserResponse, RegisterUserRequest, RegisterUserResponse } from '../../../../libs/generated';
import { ApiService } from '../../../core/services/api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private apiService : ApiService) { }

  async registerUser(firstName: string, lastName: string, email: string, password: string) : Promise<AxiosResponse<RegisterUserResponse>> {
    const request : RegisterUserRequest = {
      firstName: firstName, 
      lastName: lastName,
      email: email,
      password: password
    }
    return await this.apiService.auth.register(request);
  }

  async loginUser(email: string, password: string) : Promise<AxiosResponse<LoginUserResponse>> {
    const request : LoginUserRequest = {
      email: email,
      password: password
    }
    return await this.apiService.auth.login(request);
  }
}
