import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../config.service';
import { Config } from '../model/config';
import { PlayerInGame } from '../model/player-in-game';
import { PromptDialogComponent } from '../prompt-dialog/prompt-dialog.component';
import { MatDialog } from '@angular/material';
import { GamesService } from '../games.service';
import { AddGameRequest } from '../model/add-game-request';
import { Game } from '../model/game';
import { Participant } from '../model/participant';
import { Player } from '../model/player';
import { PlayersService } from '../players.service';
import { Host } from '../model/host';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { AdministrationService } from '../administration.service';

@Component({
  selector: 'app-add-game',
  templateUrl: './add-game.component.html',
  styleUrls: ['./add-game.component.css']
})
export class AddGameComponent implements OnInit {

  config: Config;
  playersInGame: PlayerInGame[] = [];
  players: Player[];
  host: Player;

  constructor(public dialog: MatDialog,
    private gamesService: GamesService,
    private administrationService: AdministrationService,
    private playersService: PlayersService,
    private configService: ConfigService) { }

  ngOnInit() {
    if(!this.administrationService.currentAdministrationMode) {
      window.location.href = '#';
    }

    this.playersService.getPlayers().subscribe(data => {
      this.players = data.players;
    })
    this.configService.getConfig().subscribe(data => {
      this.config = data.config;
    })
  }


  customTrackBy(index: number, obj: any): any {
    return index;
  }

  onAddPlayer() {
    this.openDialog();
  }

  onChange() {
    this.playersInGame.forEach(player => {
      player.winningsInMoney = player.winningsInChips * this.config.entryFeeInMoney / this.config.entryFeeInChips
      player.earnings = player.winningsInMoney - (player.buyIn * this.config.entryFeeInMoney);
    })

  }

  isGameCorrect() {
    var moneyInWin = 0;
    this.playersInGame.forEach(player => {
      moneyInWin += player.earnings;
    })
    return 0 === moneyInWin
  }

  getPot() {
    var pot = 0;
    this.playersInGame.forEach(player => {
      pot += player.buyIn * this.config.entryFeeInMoney;
    });
    return pot;
  }

  onSave() {
    var request = new AddGameRequest();
    request.game = new Game();
    request.game.date = new Date();
    request.game.host = new Host();
    request.game.pot = this.getPot();
    request.game.host.id = this.host.id;
    request.game.host.name = this.host.name;
    request.game.participants = this.playersInGame.map(player => new Participant(player));

    if (this.isGameCorrect()) {
      this.gamesService.addGame(request).subscribe(data => {
        console.log(data.success);
        //window.location.href = '#';
      });
    } else {
      const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
        width: '250px',
        data: {
          question: "Game is not correct. Save this game?", answer: false
        }
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        if (result === true) {
          request.skipValidation = true;
          this.gamesService.addGame(request).subscribe(data => {
            console.log(data.success);
            //window.location.href = '#';
          });
        }
      });
    }
  }

  openDialog(): void {
    var playersInGameNames = this.playersInGame.map(player => player.player.name);
    const dialogRef = this.dialog.open(PromptDialogComponent, {
      width: '250px',
      data: {
        question: "Add new player", answer: "", options: this.players
          .filter(player => playersInGameNames.indexOf(player.name) == -1)
          .map(player => player.name)
      }
    });

    dialogRef.afterClosed().subscribe(results => {
      console.log('The dialog was closed');
      if (results !== undefined) {
        results.forEach(result =>{
          this.playersInGame.push(new PlayerInGame(this.getPlayer(result)));
        });
      }
    });
  }

  isEmptyPlayers() {
    return this.playersInGame.length === 0
  }

  getPlayer(playerName: String) {
    return this.players.find(player => player.name === playerName);
  }



}
