import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GamesComponent } from './components/games/games.component';
import { AddGameComponent } from './components/add-game/add-game.component';

const routes: Routes = [
{ path: 'games', component: GamesComponent },
{ path: 'game/new', component: AddGameComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, { useHash: true })
    ],
    exports: [RouterModule]
})
export class GameRoutingModule { }
