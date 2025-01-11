import { Injectable } from '@angular/core';
import { JwtPayload, jwtDecode } from "jwt-decode";
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private cookieService: CookieService) { }

  public saveToken(token: string) {
    // Optional cookie security settings, use only if your app uses HTTPS
    // this.cookieService.set("jwtToken", token, { secure: true, sameSite: 'Strict' });
    this.cookieService.set("jwtToken", token);
  }

  public hasValidToken(): boolean {
    const token = this.cookieService.get("jwtToken");
    if (token) {
      try {
        const payload: JwtPayload = this.decodeToken(token);
        const currentTime = new Date();
        const exp = payload?.exp ? payload.exp * 1000 : null;

        if (exp) {
          const expirationTime = new Date(exp);
          if (currentTime < expirationTime) {
            return true;
          } else {
            console.log("Token is expired. Deleting token.");
            this.deleteToken();
          }
        } else {
          return true;  // Token is valid if `exp` is not present.
        }
      } catch (error) {
        console.error("Error decoding token:", error);
        this.deleteToken();
      }
    }
    return false;
  }

  public decodeToken(token: string): JwtPayload {
    try {
      return jwtDecode<JwtPayload>(token);
    } catch (error) {
      console.error("Error decoding token:", error);
      throw error;
    }
  }

  public deleteToken() {
    this.cookieService.delete("jwtToken");
  }

  public getToken(): string {
    return this.cookieService.get("jwtToken");
  }
}
