import { Component, OnInit } from '@angular/core';
import { ConfigService } from '../../../core/services/config.service';
import { FormControl } from '@angular/forms';
import { PlayersService } from '../../../core/services/players.service';
import { Player } from '../../../shared/models/player';

@Component({
  selector: 'app-winnings-chart',
  templateUrl: './winnings-chart.component.html',
  styleUrls: ['./winnings-chart.component.css']
})
export class WinningsChartComponent implements OnInit {

  title = 'Live winnings';
  playersControl = new FormControl();
  players: Player[];

  playerIds: string[];
  from;
  to;

  constructor(
    private playersService: PlayersService,
    private configService: ConfigService) { }

  ngOnInit() {
    this.playersService.getPlayers().subscribe(data => {
      this.players = data.players;
      this.playerIds = data.players.map(player => player.id)
    })
    this.configService.getConfig().subscribe(data => {
      this.from = data.config.from;
      this.to = data.config.to;
    })
  }

  onShow() {
    this.playerIds = this.playersControl.value.map(player => player.id);
  }

}
