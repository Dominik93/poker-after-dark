import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class DateFormatter {

    constructor() { }

    public format(date: Date): string {
        if (date === undefined) {
            return '';
        }
        return date.getFullYear() + '-' + this.getMonth(date) + "-" + this.getDay(date);
    }

    private getMonth(date: Date) {
        var month = date.getMonth() + 1;
        return this.addLeadingZero(month);
    }

    private getDay(date: Date) {
        return this.addLeadingZero(date.getDate());
    }

    private addLeadingZero(value: number) {
        if (value < 10) {
            return '0' + value;
        }
        return value;
    }

}