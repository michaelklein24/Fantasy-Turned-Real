import { HttpEvent, HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { inject } from "@angular/core";
import { Observable, map } from "rxjs";
import { TokenService } from "../services/token.service";

export function headerInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
    const jwtToken = inject(TokenService).getToken();
    const newHeaders = req.headers.append('X-Authentication-Token', jwtToken);
    const newReq = (req).clone({
        headers: newHeaders
    });
    return next(newReq);
}