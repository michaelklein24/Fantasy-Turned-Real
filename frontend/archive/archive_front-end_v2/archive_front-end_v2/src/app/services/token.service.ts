import { Injectable } from '@angular/core';
import { JwtPayload, jwtDecode } from "jwt-decode";
import { CookieService } from 'ngx-cookie-service';


@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private cookieService: CookieService) { }

  public saveToken(token: string) {
    this.cookieService.set("jwtToken", token);
  }

  public hasValidToken(): boolean {
    if (this.cookieService.check("jwtToken")) {
      let payload: JwtPayload = this.decodeToken(this.cookieService.get("jwtToken"));
      let currentTime = new Date();
      let exp: number = <number>payload?.exp * 1000;
      if (exp) {
        let expirationTime = new Date(exp);
        if (expirationTime && currentTime < expirationTime) {
          console.log(`Token is NOT expired.  Current time: '${currentTime}' Expiration time: ${expirationTime}`);
          return true;
        } else {
          console.log("Token is expired. Deleting token.");
          this.deleteToken();
        }
      } else {
        console.error("Unable to pull expiration time from jwtToken.  Considering Token as valid");
        return true;
      }

    }
    return false;
  }

  public decodeToken(token: string): JwtPayload {
    return jwtDecode(token);
  }

  public deleteToken() {
    this.cookieService.delete("jwtToken");
  }

  public getToken(): string {
    return this.cookieService.get("jwtToken");
  }
}
