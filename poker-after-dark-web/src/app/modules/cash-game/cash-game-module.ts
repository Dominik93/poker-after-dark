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
import { CashGameRoutingModule } from './cash-game-routing.module';
import { AddCashGameComponent } from './components/add-game/add-cash-game.component';

@NgModule({
    declarations: [
        AddCashGameComponent,
    ],
    entryComponents: [
        PromptDialogComponent,
        LoginDialogComponent,
        ConfirmationDialogComponent
    ],
    imports: [
        CashGameRoutingModule,
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
export class CashGameModule { }
