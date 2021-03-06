import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';
import { PromptDialogComponent } from 'src/app/shared/components/prompt-dialog/prompt-dialog.component';
import { LoginDialogComponent } from 'src/app/core/login-dialog/login-dialog.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/app/material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { CoreModule } from 'src/app/core/core.module';
import { TournamentRoutingModule } from './tournament-routing.module';
import { AddTournamentComponent } from './components/add-tournament.component';

@NgModule({
    declarations: [
        AddTournamentComponent,
    ],
    entryComponents: [
        PromptDialogComponent,
        LoginDialogComponent,
        ConfirmationDialogComponent
    ],
    imports: [
        TournamentRoutingModule,
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
export class TournamentModule { }
