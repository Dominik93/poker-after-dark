import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddPlayerComponent } from './components/add-player/add-player.component';
import { PlayersComponent } from './components/players/players.component';
import { PlayerComponent } from './components/player/player.component';

const routes: Routes = [
{ path: 'players', component: PlayersComponent },
{ path: 'players/details/:id', component: PlayerComponent },
{ path: 'players/create', component: AddPlayerComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, { useHash: true })
    ],
    exports: [RouterModule]
})
export class PlayerRoutingModule { }
