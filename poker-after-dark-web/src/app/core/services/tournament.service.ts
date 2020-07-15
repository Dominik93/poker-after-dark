import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { AddTournamentRequest } from 'src/app/shared/models/tournament/add-trournament-request';
import { AddTournamentResponse } from 'src/app/shared/models/tournament/add-tournament-response';

@Injectable({
  providedIn: 'root'
})
export class TournamentService {

  private path: string = environment.baseUrl + "games/tournament";

  constructor(private http: HttpClient) { }

  public addTournament(request: AddTournamentRequest) {
    return this.http.post<AddTournamentResponse>(this.path, request);
  }
}

