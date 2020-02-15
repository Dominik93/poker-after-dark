import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';
import { AddPlayerComponent } from './components/add-player/add-player.component';
import { PlayersComponent } from './components/players/players.component';
import { PromptDialogComponent } from 'src/app/shared/components/prompt-dialog/prompt-dialog.component';
import { LoginDialogComponent } from 'src/app/core/login-dialog/login-dialog.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { MaterialModule } from 'src/app/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { PlayerRoutingModule } from './player-routing.module';
import { CoreModule } from 'src/app/core/core.module';
import { PlayerComponent } from './components/player/player.component';

@NgModule({
  declarations: [
    PlayersComponent,
    PlayerComponent,
    AddPlayerComponent,
  ],
  entryComponents: [
    PromptDialogComponent,
    LoginDialogComponent,
    ConfirmationDialogComponent
  ],
  imports: [
    PlayerRoutingModule,
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
export class PlayerModule { }
