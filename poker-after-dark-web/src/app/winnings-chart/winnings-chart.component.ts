import { Component, OnInit } from '@angular/core';
import * as CanvasJS from './../../../node_modules/canvasjs-2.3.2/canvasjs.min';
import { ProfitService } from '../profit.service';
import { ConfigService } from '../config.service';
import { Config } from '../model/config';
import { GetProfitRequest } from '../model/get-profit-request';
import { PlayersService } from '../players.service';
import { Player } from '../model/player';
import { FormControl } from '@angular/forms';
@Component({
  selector: 'app-winnings-chart',
  templateUrl: './winnings-chart.component.html',
  styleUrls: ['./winnings-chart.component.css']
})
export class WinningsChartComponent implements OnInit {

  playersControl = new FormControl();
  players: Player[];
  config: Config;

  constructor(
    private playersSerice: PlayersService,
    private configService: ConfigService,
    private profitService: ProfitService) { }



  ngOnInit() {
    this.playersSerice.getPlayers().subscribe(data => {
      this.players = data.players;
    })
    this.configService.getConfig().subscribe(data => {
      this.config = data.config;
      var request = new GetProfitRequest();
      request.playersIds = [];
      request.from = this.config.from;
      request.to = this.config.to;
      this.renderChart(request);
    })
  }

  onShow(){
    var request = new GetProfitRequest();
    request.playersIds = this.playersControl.value.map(player => player.id);
    request.from = this.config.from;
    request.to = this.config.to;
    this.renderChart(request);
  }

  renderChart(request) {
    var datas = [];
    this.profitService.getProfit(request).subscribe(data => {
      data.profits.forEach(profit => {
        var points = [];
        profit.dataPoints.forEach(amount => points.push({ y: amount }));
        datas.push({
          type: "line",
          name: profit.playerName,
          showInLegend: true,
          dataPoints: points
        })
      })

      let chart = new CanvasJS.Chart("chartContainer", {
        zoomEnabled: true,
        animationEnabled: true,
        exportEnabled: true,
        legend: {
          fontSize: 10
        },
        title: {
          text: "Live Winnings"
        },
        data: datas
      });

      chart.render();

    })
  }

}
