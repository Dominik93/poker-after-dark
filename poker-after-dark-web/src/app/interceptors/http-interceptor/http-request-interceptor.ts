import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { ResponseHandler } from './reponse-handler';
import { AdministrationService } from 'src/app/administration.service';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
    constructor(private injector: Injector,
        private administrationService: AdministrationService,
        private responseHandler: ResponseHandler) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authRequest = request.clone({
            headers: request.headers.set('Authentication', this.administrationService.token)
        });

        return next.handle(authRequest).pipe(
            tap(evt => {
                this.responseHandler.handle(evt);
            }),
            catchError((err: any) => {
                return this.responseHandler.handlerError(err);
            }));
    }

}
