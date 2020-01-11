import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GamesComponent } from './games/games.component';
import { WinningsChartComponent } from './winnings-chart/winnings-chart.component';
import { HomeComponent } from './home/home.component';
import { AddGameComponent } from './add-game/add-game.component';
import { PlayersComponent } from './players/players.component';
import { AddPlayerComponent } from './add-player/add-player.component';
import { AdministrationGamesComponent } from './administration-games/administration-games.component';
import { AdministrationPlayersComponent } from './administation-players/administration-players.component';

const routes: Routes = [
{ path: '', component: HomeComponent },
{ path: 'players', component: PlayersComponent },
{ path: 'players/new', component: AddPlayerComponent },
{ path: 'games', component: GamesComponent },
{ path: 'administration/games', component: AdministrationGamesComponent },
{ path: 'administration/players', component: AdministrationPlayersComponent },
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
