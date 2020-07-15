import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { GetPlayersResponse } from 'src/app/shared/models/player/get-players-response';
import { AddPlayerRequest } from 'src/app/shared/models/player/add-player-request';
import { AddPlayerResponse } from 'src/app/shared/models/player/add-player-response';
import { GetPlayerResponse } from 'src/app/shared/models/player/get-player-response';


@Injectable({
  providedIn: 'root'
})
export class PlayersService {

  private path: string = environment.baseUrl + "players";

  constructor(private http: HttpClient) { }

  public getPlayer(id: string) {
    return this.http.get<GetPlayerResponse>(this.path + '/' + id);
  }

  public getPlayers() {
    return this.http.get<GetPlayersResponse>(this.path);
  }

  public addplayer(request: AddPlayerRequest) {
    return this.http.post<AddPlayerResponse>(this.path, request);
  }
}
