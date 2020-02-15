import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { GetGamesRequest } from 'src/app/shared/models/get-games-request';
import { GetGamesResponse } from 'src/app/shared/models/get-games-response';
import { AddGameRequest } from 'src/app/shared/models/add-game-request';
import { AddGameResponse } from 'src/app/shared/models/add-game-response';
import { RemoveGameResponse } from 'src/app/shared/models/remove-game-response';
import { RemoveGameRequest } from 'src/app/shared/models/remove-game-request';

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

