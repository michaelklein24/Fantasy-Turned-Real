import { Injectable } from '@angular/core';
import { JwtPayload, jwtDecode } from "jwt-decode";
import { CookieService } from 'ngx-cookie-service';
import { interval } from 'rxjs';


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
      if (new Date() < new Date(<number>payload?.exp)) {
        return true;
      } else {
        console.log("Token is expired. Deleting token.")
        this.deleteToken();
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

}
