import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { GetGamesResponse } from './model/get-games-response';
import { AddGameResponse } from './model/add-game-response';
import { AddGameRequest } from './model/add-game-request';
import { GetGamesRequest } from './model/get-games-request';
import { RemoveGameRequest } from './model/remove-game-request';
import { RemoveGameResponse } from './model/remove-game-response';

@Injectable({
  providedIn: 'root'
})
export class GamesService {

  private path: string = environment.baseUrl + "games";

  constructor(private http: HttpClient) { }

  public getGames(request: GetGamesRequest) {
    return this.http.post<GetGamesResponse>(this.path + "/pages", request);
  }

  public addGame(request: AddGameRequest) {
    return this.http.post<AddGameResponse>(this.path, request);
  }

  public removeGame(request: RemoveGameRequest) {
    return this.http.delete<RemoveGameResponse>(this.path + "/" + request.id);
  }
}

