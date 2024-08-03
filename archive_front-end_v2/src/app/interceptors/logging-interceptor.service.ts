import { HttpEvent, HttpEventType, HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { Observable, tap } from "rxjs";

export function loggingInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
    console.log(`
============= Request Begin =============
url: ${req.urlWithParams}
method: ${req.method}
headers: ${JSON.stringify(req.headers.keys().map(key => ({ [key]: req.headers.get(key) })))}
body: ${JSON.stringify(req.body, null, 2)}
============== Request End ==============
        `);

    return next(req)
        .pipe(
            tap(event => {
                if (event.type === HttpEventType.Response) {
                    console.log(`
============= Response Begin =============
url: ${event.url}
status: ${event.status + " " + event.statusText}
headers: ${JSON.stringify(event.headers.keys().map(key => ({ [key]: req.headers.get(key) })))}
body: ${JSON.stringify(event.body, null, 2)}
============== Response End ==============
        `);
                }
            }));
}