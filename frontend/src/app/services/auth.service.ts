import { Injectable } from '@angular/core';
import { AxiosResponse } from 'axios';
import apiClient, { LoginUserRequest, LoginUserResponse, RegisterUserRequest, RegisterUserResponse } from '../shared/ApiClient';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  async registerUser(firstName: string, lastName: string, email: string, password: string) : Promise<AxiosResponse<RegisterUserResponse>> {
    const request : RegisterUserRequest = {
      firstName: firstName, 
      lastName: lastName,
      email: email,
      password: password
    }
    return await apiClient.auth.register(request, "");
  }

  async loginUser(email: string, password: string) : Promise<AxiosResponse<LoginUserResponse>> {
    const request : LoginUserRequest = {
      email: email,
      password: password
    }
    return await apiClient.auth.login(request);
  }
}
