import { Injectable } from '@angular/core';

import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdministrationService {

  private administrationMode = new Subject<boolean>();

  currentAdministrationMode;

  constructor() { }

  
  setAdministrationMode(administrationMode: boolean) {
    this.currentAdministrationMode = administrationMode;
    this.administrationMode.next(administrationMode);
  }

  getAdministrationMode() {
    return this.administrationMode.asObservable();
  }

}

