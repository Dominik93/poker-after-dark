import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { WinningsChartComponent } from './winnings-chart/winnings-chart.component';
import { GamesComponent } from './games/games.component';
import { AddGameComponent } from './add-game/add-game.component';
import { HomeComponent } from './home/home.component';
import { AppRoutingModule } from './app-routing.module';
import { MaterialModule } from './material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MenuComponent } from './menu/menu.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import { PromptDialogComponent } from './prompt-dialog/prompt-dialog.component';
import { PlayersComponent } from './players/players.component';
import { AddPlayerComponent } from './add-player/add-player.component';
import { MatNativeDateModule } from '@angular/material';
import { CdkDetailRowDirective } from './cdk-detail-row-directive';
import { ConfirmationDialogComponent } from './confirmation-dialog/confirmation-dialog.component';
import { AdministrationGamesComponent } from './administration-games/administration-games.component';
import { AdministrationPlayersComponent } from './administation-players/administration-players.component';
  

@NgModule({
  declarations: [
    CdkDetailRowDirective,
    AppComponent,
    WinningsChartComponent,
    GamesComponent,
    AdministrationGamesComponent,
    AdministrationPlayersComponent,
    AddGameComponent,
    MenuComponent,
    HomeComponent,
    PromptDialogComponent,
    PlayersComponent,
    AddPlayerComponent,
    ConfirmationDialogComponent
  ],
  entryComponents: [
    PromptDialogComponent,
    ConfirmationDialogComponent
  ],
  imports: [
    AppRoutingModule,
    MaterialModule,
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    HttpModule,
    HttpClientModule
  ],
  
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
