import { Injectable, OnDestroy } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { TokenService } from './token.service';

@Injectable({
    providedIn: 'root'
})
export class SessionService implements OnDestroy {

    private _loginStatus = new BehaviorSubject<boolean>(this.tokenService.hasValidToken());
    private _userId = new BehaviorSubject<string | null>(this.tokenService.hasValidToken() ? ((<any>this.tokenService.decodeToken(this.tokenService.getToken())).userId) : null);

    public userId$ = this._userId.asObservable();
    public loginStatus$ = this._loginStatus.asObservable();

    private tokenCheckInterval : any;

    constructor(private tokenService: TokenService) {
        this.startTokenCheck();
    }

    private setUserId(id: string | null) {
        this._userId.next(id);
    }

    private setloginStatus(isUserLoggedIn: boolean) {
        this._loginStatus.next(isUserLoggedIn);
    }

    public startSession(token: string): void {
        if (token) {
            this.tokenService.saveToken(token);
        }
        if (this.tokenService.hasValidToken()) {
            let userId = (<any>this.tokenService.decodeToken(token))['userId'];
            this.setUserId(userId);
            this.setloginStatus(true);
            return;
        }
        this.setUserId(null);
        this.setloginStatus(false);
    }

    public clearSession(): void {
        this.tokenService.deleteToken();
        this.setUserId(null);
        this.setloginStatus(false);
    }

    private startTokenCheck() {
        this.tokenCheckInterval = setInterval(() => {
            this.tokenService.hasValidToken();
        }, 60000); // Check every 60 seconds
    }

    ngOnDestroy(): void {
        if (this.tokenCheckInterval) {
            clearInterval(this.tokenCheckInterval);
        }
    }
}
