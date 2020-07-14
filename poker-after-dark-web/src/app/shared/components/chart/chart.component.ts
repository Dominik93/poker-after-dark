import { Component, OnInit, Input } from '@angular/core';
import * as CanvasJS from 'canvasjs-2.3.2/canvasjs.min';
import { ProfitService } from '../../../core/services/profit.service';
import { GetProfitRequest } from '../../models/profit/get-profit-request';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  @Input() title = 'Chart';

  _gameTypes: string[];

  _playerIds: string[];

  _from: Date;

  _to: Date;

  constructor(private profitService: ProfitService) { }
  
  @Input()
  set from(from) {
    this._from = from;
    this.init();
  }

  @Input()
  set to(to) {
    this._to = to;
    this.init();
  }
  
  @Input()
  set gameTypes(gameTypes) {
    this._gameTypes = gameTypes;
    this.init();
  }

  @Input()
  set playerIds(playersIds) {
    this._playerIds = playersIds;
    this.init();
  }

  ngOnInit() {
  }

  init() {
    if (!(this._gameTypes === undefined || this._playerIds === undefined)) {
      var request = new GetProfitRequest();
      request.playersIds = this._playerIds;
      request.from = this._from;
      request.to = this._to;
      request.gameTypes = this._gameTypes;
      this.renderChart(request);
    }
  }

  renderChart(request) {
    this.profitService.getProfit(request).subscribe(data => {
      var datas = this.prepareDataPoints(data);
      let chart = this.initChart(datas)
      chart.render();
    })
  }

  prepareDataPoints(data) {
    var datas = [];
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
    return datas;
  }

  initChart(datas) {
    let chart = new CanvasJS.Chart("chartContainer", {
      zoomEnabled: true,
      animationEnabled: true,
      exportEnabled: true,
      legend: {
        fontSize: 10
      },
      title: {
        text: this.title
      },
      data: datas
    });
    return chart;
  }

}
