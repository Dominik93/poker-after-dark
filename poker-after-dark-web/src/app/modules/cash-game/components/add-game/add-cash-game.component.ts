import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../../../../core/services/config.service';
import { PromptDialogComponent } from '../../../../shared/components/prompt-dialog/prompt-dialog.component';
import { MatDialog } from '@angular/material';
import { GamesService } from '../../../../core/services/games.service';
import { ConfirmationDialogComponent } from '../../../../shared/components/confirmation-dialog/confirmation-dialog.component';
import { AdministrationService } from '../../../../core/services/administration.service';
import { FormControl } from '@angular/forms';
import { DateFormatter } from '../../../../shared/date/date-formatter';
import { PlayersService } from 'src/app/core/services/players.service';
import { Config } from 'src/app/shared/models/config/config';
import { PlayerInGame } from 'src/app/shared/models/cash-game/player-in-game';
import { Game } from 'src/app/shared/models/game/game';
import { Host } from 'src/app/shared/models/game/host';
import { Player } from 'src/app/shared/models/player/player';
import { AddCashGameRequest } from 'src/app/shared/models/cash-game/add-cash-game-request';
import { CashGame } from 'src/app/shared/models/cash-game/cash-game';
import { CashGameService } from 'src/app/core/services/cash-game.service';
import { CashGameParticipant } from 'src/app/shared/models/cash-game/cash-game-participant';

@Component({
  selector: 'app-add-cash-game',
  templateUrl: './add-cash-game.component.html',
  styleUrls: ['./add-cash-game.component.css']
})
export class AddCashGameComponent implements OnInit {

  config: Config;
  playersInGame: PlayerInGame[] = [];
  players: Player[];
  host: Player;
  date = new FormControl(new Date());

  constructor(public dialog: MatDialog,
    private gamesService: CashGameService,
    private dateFormatter: DateFormatter,
    private administrationService: AdministrationService,
    private playersService: PlayersService,
    private configService: ConfigService) { }

  ngOnInit() {
    if (!this.administrationService.currentAdministrationMode) {
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
    var request = new AddCashGameRequest();
    request.skipValidation = false;
    request.game = new CashGame();
    request.game.date = this.dateFormatter.format(this.date.value);
    request.game.pot = this.getPot();
    request.game.host = new Host();
    request.game.host.id = this.host.id;
    request.game.host.name = this.host.name;
    request.game.participants = this.playersInGame.map(player => new CashGameParticipant(player));

    if (this.isGameCorrect()) {
      this.gamesService.addCashGame(request).subscribe(data => {
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
          this.gamesService.addCashGame(request).subscribe(data => {
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
        results.forEach(result => {
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
