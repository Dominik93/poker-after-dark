import { Injectable, Injector } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { MatSnackBar } from '@angular/material';
import { tap, catchError } from 'rxjs/operators';
import { ResponseHandler } from './reponse-handler';

@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {
    constructor(private injector: Injector,
        private responseHandler: ResponseHandler) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            tap(evt => {
                this.responseHandler.handle(evt);
            }),
            catchError((err: any) => {
                return this.responseHandler.handlerError(err);
            }));
    }

}
