import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { GetGamesRequest } from 'src/app/shared/models/game/get-games-request';
import { GetGamesResponse } from 'src/app/shared/models/game/get-games-response';
import { RemoveGameResponse } from 'src/app/shared/models/game/remove-game-response';
import { RemoveGameRequest } from 'src/app/shared/models/game/remove-game-request';
import { AddCashGameResponse } from 'src/app/shared/models/cash-game/add-cash-game-response';

@Injectable({
  providedIn: 'root'
})
export class GamesService {

  private path: string = environment.baseUrl + "games";

  constructor(private http: HttpClient) { }

  public getGames(request: GetGamesRequest) {
    return this.http.post<GetGamesResponse>(this.path + "/pages", request);
  }

  public removeGame(request: RemoveGameRequest) {
    return this.http.delete<RemoveGameResponse>(this.path + "/" + request.id);
  }
}

