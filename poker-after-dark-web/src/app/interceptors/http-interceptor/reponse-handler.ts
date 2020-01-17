import { HttpResponse, HttpErrorResponse } from "@angular/common/http";
import { of } from 'rxjs';
import { Injector, Injectable } from "@angular/core";
import { MatSnackBar } from "@angular/material";

@Injectable({
    providedIn: 'root'
  })
export class ResponseHandler {

    constructor(private snackBar: MatSnackBar) { }

    handle(evt: any) {
        if (evt instanceof HttpResponse) {
            if (evt.body && evt.body.success !== undefined) {
                if (evt.body.success) {
                    this.openSnackBar("Operation successful");
                } else {
                    this.openSnackBar("Something went wrong");
                }
            }
        }
    }

    handlerError(err: any) {
        if (err instanceof HttpErrorResponse) {
            this.openSnackBar("Something went wrong");
        }
        return of(err);
    }

    private openSnackBar(message: string) {
        this.snackBar.open(message, "Close", {
            duration: 2000,
        });
    }

}