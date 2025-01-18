import { Injectable, OnDestroy } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { TokenService } from './token.service';
import { WebSocketService } from '../../../core/services/web-socket.service';

@Injectable({
    providedIn: 'root'
})
export class SessionService implements OnDestroy {

    private _loginStatus = new BehaviorSubject<boolean>(this.tokenService.hasValidToken());
    private _userId = new BehaviorSubject<string | null>(this.tokenService.hasValidToken() ? this.extractUserId() : null);

    public userId$ = this._userId.asObservable();
    public loginStatus$ = this._loginStatus.asObservable();

    private tokenCheckInterval: any;

    constructor(
        private tokenService: TokenService
    ) {
        this.startTokenCheck();
    }

    private extractUserId(): string | null {
        const token = this.tokenService.getToken();
        if (token) {
            try {
                return (<any>this.tokenService.decodeToken(token))['userId'];
            } catch (error) {
                console.error('Invalid token format', error);
                return null;
            }
        }
        return null;
    }

    private setUserId(id: string | null) {
        this._userId.next(id);
    }

    private setLoginStatus(isUserLoggedIn: boolean) {
        this._loginStatus.next(isUserLoggedIn);
    }

    public startSession(token: string): void {
        if (token) {
            this.tokenService.saveToken(token);
        }
        const isValid = this.tokenService.hasValidToken();
        if (isValid) {
            const userId = this.extractUserId();
            this.setUserId(userId);
            this.setLoginStatus(true);
        } else {
            this.setUserId(null);
            this.setLoginStatus(false);
        }
    }

    public clearSession(): void {
        this.tokenService.deleteToken();
        this.setUserId(null);
        this.setLoginStatus(false);
    }

    private startTokenCheck() {
        this.tokenCheckInterval = setInterval(() => {
            if (!this.tokenService.hasValidToken()) {
                this.clearSession();
            }
        }, 60000);
    }

    ngOnDestroy(): void {
        if (this.tokenCheckInterval) {
            clearInterval(this.tokenCheckInterval);
        }
    }
}
