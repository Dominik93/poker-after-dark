import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GamesComponent } from './modules/game/games/games.component';
import { WinningsChartComponent } from './shared/components/winnings-chart/winnings-chart.component';
import { HomeComponent } from './core/home/home.component';
import { AddGameComponent } from './modules/game/add-game/add-game.component';
import { PlayersComponent } from './modules/player/players/players.component';
import { AddPlayerComponent } from './modules/player/add-player/add-player.component';

const routes: Routes = [
{ path: '', component: HomeComponent },
{ path: 'players', component: PlayersComponent },
{ path: 'players/new', component: AddPlayerComponent },
{ path: 'games', component: GamesComponent },
{ path: 'game/new', component: AddGameComponent },
{ path: 'chart', component: WinningsChartComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, { useHash: true })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule { }
