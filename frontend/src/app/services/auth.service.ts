import { Injectable } from '@angular/core';
import { AxiosResponse } from 'axios';
import apiClient, { RegisterUserRequest, RegisterUserResponse } from '../shared/ApiClient';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  async registerUser(firstName: string, lastName: string, email: string, password: string) : Promise<AxiosResponse<RegisterUserResponse>> {
    const request : RegisterUserRequest = {
      firstName: "michael",
      lastName: "klein",
      email: "email",
      password: "password"
    }
    return await apiClient.auth.register(request);
  }
}
