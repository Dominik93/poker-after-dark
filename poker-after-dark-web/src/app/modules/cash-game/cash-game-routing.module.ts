import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCashGameComponent } from './components/add-game/add-cash-game.component';

const routes: Routes = [
{ path: 'cash-game/new', component: AddCashGameComponent }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes, { useHash: true })
    ],
    exports: [RouterModule]
})
export class CashGameRoutingModule { }
