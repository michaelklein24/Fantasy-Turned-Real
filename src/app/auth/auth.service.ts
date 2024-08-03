import { Injectable } from '@angular/core';
import { LoginRequest } from './dto/login-request.model';
import { LoginResponse } from './dto/login-response.model';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { RegisterRequest } from './dto/register-request.model';
import { RegisterResponse } from './dto/register-response.model';
import { BehaviorSubject, Observable, interval, map, tap } from 'rxjs';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {

  private loggedIn = new BehaviorSubject<boolean>(this.tokenService.hasValidToken());

  constructor(private http: HttpClient, private tokenService: TokenService) { }


  public login(username: string, password: string): void {
    let loginRequest: LoginRequest = new LoginRequest(username, password);
    this.http.post<LoginResponse>("http://localhost:8080/api/auth/login", loginRequest, { observe: 'response' })
      .pipe(
        tap((response) => { console.log(response); }),
        map((response) => response.body)
      )
      .subscribe(
        {
          next: () => {
            this.tokenService.saveToken(this)
            this.setLoggedIn(true);
          },
          error: console.log(this)
        }
        // this.tokenService.saveToken(body.accessToken);
      );
  }

  public register(firstName: string, lastName: string, username: string, email: string, password: string): void {
    let registerRequest: RegisterRequest = new RegisterRequest(firstName, lastName, username, email, password);
    this.http.post<RegisterResponse>("http://localhost:8080/api/auth/register", registerRequest).subscribe((response: RegisterResponse) => {
      console.log(response.accessToken);
      this.tokenService.saveToken(response.accessToken);
    });
    this.setLoggedIn(true);
  }

  public isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  private setLoggedIn(value: boolean) {
    this.loggedIn.next(value);
  }

  public logout(): void {
    this.tokenService.deleteToken();
    this.setLoggedIn(false);
  }
}
