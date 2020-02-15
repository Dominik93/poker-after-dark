import { Injectable } from '@angular/core';

import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdministrationService {

  private administrationMode = new Subject<boolean>();

  token: string = "";

  currentAdministrationMode;

  constructor() { }

  
  setAdministrationMode(administrationMode: boolean, token: string) {
    this.currentAdministrationMode = administrationMode;
    this.token = token;
    this.administrationMode.next(administrationMode);
  }

  getAdministrationMode() {
    return this.administrationMode.asObservable();
  }

}

