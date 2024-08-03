import { HttpEvent, HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";
import { TokenService } from "../services/token.service";
import { inject } from "@angular/core";

export function tokenInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
    let token = inject(TokenService).getToken();
    let requestWithAuthHeader: HttpRequest<unknown> = req.clone({
        headers: req.headers.set("Authorization", `Bearer ${token}`)
    });

    return next(requestWithAuthHeader);
}
