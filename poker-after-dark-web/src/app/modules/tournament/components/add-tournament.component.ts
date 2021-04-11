import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { FormControl } from '@angular/forms';
import { PlayersService } from 'src/app/core/services/players.service';
import { Config } from 'src/app/shared/models/config/config';
import { Host } from 'src/app/shared/models/game/host';
import { Player } from 'src/app/shared/models/player/player';
import { DateFormatter } from 'src/app/shared/date/date-formatter';
import { AdministrationService } from 'src/app/core/services/administration.service';
import { ConfigService } from 'src/app/core/services/config.service';
import { TournamentService } from 'src/app/core/services/tournament.service';
import { AddTournamentRequest } from 'src/app/shared/models/tournament/add-trournament-request';
import { Tournament } from 'src/app/shared/models/tournament/tournament';
import { TournamentParticipant } from 'src/app/shared/models/tournament/tournament-participant';
import { PromptDialogComponent } from 'src/app/shared/components/prompt-dialog/prompt-dialog.component';
import { PlayerInTournament } from 'src/app/shared/models/tournament/player-in-tournament';

@Component({
  selector: 'app-add-tournament',
  templateUrl: './add-tournament.component.html',
  styleUrls: ['./add-tournament.component.css']
})
export class AddTournamentComponent implements OnInit {

  config: Config;
  playersInGame: PlayerInTournament[] = [];
  players: Player[];
  host: Player;
  buyIn: number;
  date = new FormControl(new Date());

  constructor(public dialog: MatDialog,
    private tournamentService: TournamentService,
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
    var pot = this.getPot();
    this.playersInGame.forEach(player => {
      player.winningsInMoney = player.percent / 100 * pot;
      player.earnings = player.winningsInMoney - this.buyIn;
    })
  }

  getPot() {
    return this.buyIn * this.playersInGame.length;
  }

  onSave() {
    var request = new AddTournamentRequest();
    request.tournament = new Tournament();
    request.tournament.date = this.dateFormatter.format(this.date.value);
    request.tournament.host = new Host();
    request.tournament.host.id = this.host.id;
    request.tournament.host.name = this.host.name;
    request.tournament.pot = this.getPot();
    request.tournament.participants = this.playersInGame.map(player => new TournamentParticipant(player));

    this.tournamentService.addTournament(request).subscribe(data => {
      //window.location.href = '#';
    });
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
      if (results !== undefined) {
        results.forEach(result => {
          this.playersInGame.push(new PlayerInTournament(this.getPlayer(result)));
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
