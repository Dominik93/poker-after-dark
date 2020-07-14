import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { AddCashGameResponse } from 'src/app/shared/models/cash-game/add-cash-game-response';
import { AddCashGameRequest } from 'src/app/shared/models/cash-game/add-cash-game-request';

@Injectable({
  providedIn: 'root'
})
export class CashGameService {

  private path: string = environment.baseUrl + "games/cash";

  constructor(private http: HttpClient) { }

  public addCashGame(request: AddCashGameRequest) {
    return this.http.post<AddCashGameResponse>(this.path, request);
  }
}

