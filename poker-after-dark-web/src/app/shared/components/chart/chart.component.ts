import { Component, OnInit, Input } from '@angular/core';
import * as CanvasJS from 'canvasjs-2.3.2/canvasjs.min';
import { ProfitService } from '../../../core/services/profit.service';
import { GetProfitRequest } from '../../models/get-profit-request';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {

  @Input() title = 'Chart';

  @Input() playerIds: string[];

  @Input() from: Date;

  @Input() to: Date;

  constructor(private profitService: ProfitService) { }

  ngOnInit() {
    var request = new GetProfitRequest();
    request.playersIds = this.playerIds;
    request.from = this.from;
    request.to = this.to;
    this.renderChart(request);
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
