import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { GamesService } from '../../../../core/services/games.service';
import { Router } from '@angular/router';
import { ConfigService } from '../../../../core/services/config.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { AdministrationService } from '../../../../core/services/administration.service';
import { PlayersService } from 'src/app/core/services/players.service';
import { Game } from 'src/app/shared/models/game';
import { Config } from 'src/app/shared/models/config';
import { Player } from 'src/app/shared/models/player';
import { RemoveGameRequest } from 'src/app/shared/models/remove-game-request';
import { GetGamesRequest } from 'src/app/shared/models/get-games-request';

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
  displayedColumns = ["date", "host", "players", "pot", "actions"];
  dataSource: MatTableDataSource<Game>;
  games: Game[];
  players: Player[];
  config: Config;

  @ViewChild(MatSort) sort: MatSort;

  isExpansionDetailRow = (index, row) => row.hasOwnProperty('detailRow');

  constructor(private router: Router,
    private administrationService: AdministrationService,
    private configService: ConfigService,
    private playersService: PlayersService,
    private gamesService: GamesService) { }

  ngOnInit() {
    this.administrationMode = this.administrationService.currentAdministrationMode;
    this.administrationService.getAdministrationMode().subscribe(data => {
      this.administrationMode = data;
    })

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
