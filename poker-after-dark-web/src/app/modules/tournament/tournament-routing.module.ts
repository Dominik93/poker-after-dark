import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddTournamentComponent } from './components/add-tournament.component';

const routes: Routes = [
{ path: 'tournament/new', component: AddTournamentComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, { useHash: true })
    ],
    exports: [RouterModule]
})
export class TournamentRoutingModule { }
