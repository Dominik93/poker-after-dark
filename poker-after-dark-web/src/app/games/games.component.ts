import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { GamesService } from '../games.service';
import { Game } from '../model/game';
import { Router, ActivatedRoute } from '@angular/router';
import { GetGamesRequest } from '../model/get-games-request';
import { Player } from '../model/player';
import { PlayersService } from '../players.service';
import { Config } from '../model/config';
import { ConfigService } from '../config.service';
import { animate, state, style, transition, trigger } from '@angular/animations';

import { RemoveGameRequest } from '../model/remove-game-request';
@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  animations: [
    trigger('detailExpand', [
      state('void', style({ height: '0px', minHeight: '0', visibility: 'hidden' })),
      state('*', style({ height: '*', visibility: 'visible' })),
      transition('void <=> *', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
  styleUrls: ['./games.component.css']

})
export class GamesComponent implements OnInit {

  administrationMode: boolean = false;
  displayedColumns = ["date", "host", "players", "pot"];
  dataSource: MatTableDataSource<Game>;
  games: Game[];
  players: Player[];
  config: Config;

  @ViewChild(MatSort) sort: MatSort;

  isExpansionDetailRow = (index, row) => row.hasOwnProperty('detailRow');

  constructor(private router: Router,
    private configService: ConfigService,
    private playersService: PlayersService,
    private gamesService: GamesService) { }

  ngOnInit() {
    this.playersService.getPlayers().subscribe(data => {
      this.players = data.players;
      this.configService.getConfig().subscribe(data => {
        this.config = data.config;
        this.getGames();
      })
    })
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  onAddGame() {
    this.router.navigate(['/game/new']);
  }

  onRemoveGame(game: Game) {
    var request = new RemoveGameRequest();
    request.id = game.id;
    this.gamesService.removeGame(request).subscribe(data => {
      console.log(data.success);
      const index = this.games.indexOf(game, 0);
      if (index > -1) {
        this.games.splice(index, 1);
        this.dataSource = new MatTableDataSource(this.games);
        this.dataSource.sort = this.sort;
      }
    })
  }

  getGames() {
    var request = new GetGamesRequest();
    request.playersIds = this.players.map(player => player.id);
    request.from = this.config.from;
    request.to = this.config.to;
    this.gamesService.getGames(request).subscribe(data => {
      this.games = data.games;
      this.dataSource = new MatTableDataSource(this.games);
      this.dataSource.sort = this.sort;
    })
  }

  getPlayerName(playerId: string) {
    return this.players.find(player => player.id === playerId).name;
  }


}
