import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WinningsChartComponent } from './components/winnings-chart.component';

const routes: Routes = [
{ path: 'chart', component: WinningsChartComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, { useHash: true })
    ],
    exports: [RouterModule]
})
export class WinningsChartRoutingModule { }
