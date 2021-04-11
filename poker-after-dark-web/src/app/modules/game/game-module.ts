import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';
import { GamesComponent } from './components/games/games.component';
import { PromptDialogComponent } from 'src/app/shared/components/prompt-dialog/prompt-dialog.component';
import { LoginDialogComponent } from 'src/app/core/login-dialog/login-dialog.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/app/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { CoreModule } from 'src/app/core/core.module';
import { GameRoutingModule } from './game-routing.module';
import { PokerTableComponent } from './components/poker-table/poker-table.component';

@NgModule({
    declarations: [
        GamesComponent,
        PokerTableComponent
    ],
    entryComponents: [
        PromptDialogComponent,
        LoginDialogComponent,
        ConfirmationDialogComponent
    ],
    imports: [
        GameRoutingModule,
        CoreModule,
        MaterialModule,
        BrowserAnimationsModule,
        BrowserModule,
        FormsModule,
        MatNativeDateModule,
        ReactiveFormsModule,
        HttpModule,
        HttpClientModule
    ]
})
export class GameModule { }
