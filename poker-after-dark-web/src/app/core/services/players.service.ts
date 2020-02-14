import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { GetPlayersResponse } from 'src/app/shared/models/get-players-response';
import { AddPlayerRequest } from 'src/app/shared/models/add-player-request';
import { AddPlayerResponse } from 'src/app/shared/models/add-player-response';


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
