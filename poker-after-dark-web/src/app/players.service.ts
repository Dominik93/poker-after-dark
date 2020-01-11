import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { GetPlayersResponse } from './model/get-players-response';
import { AddPlayerRequest } from './model/add-player-request';
import { AddPlayerResponse } from './model/add-player-response';

@Injectable({
  providedIn: 'root'
})
export class PlayersService {

  private path: string = environment.baseUrl + "players";

  constructor(private http: HttpClient) { }

  public getPlayers() {
    return this.http.get<GetPlayersResponse>(this.path);
  }

  public addplayer(request: AddPlayerRequest) {
    return this.http.post<AddPlayerResponse>(this.path, request);
  }
}
