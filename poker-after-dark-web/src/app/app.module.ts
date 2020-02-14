import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { WinningsChartComponent } from './shared/components/winnings-chart/winnings-chart.component';
import { GamesComponent } from './modules/game/games/games.component';
import { AddGameComponent } from './modules/game/add-game/add-game.component';
import { HomeComponent } from './core/home/home.component';
import { AppRoutingModule } from './app-routing.module';
import { MaterialModule } from './material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MenuComponent } from './core/menu/menu.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; 
import { PromptDialogComponent } from './shared/components/prompt-dialog/prompt-dialog.component';
import { PlayersComponent } from './modules/player/players/players.component';
import { AddPlayerComponent } from './modules/player/add-player/add-player.component';
import { MatNativeDateModule } from '@angular/material';
import { CdkDetailRowDirective } from './shared/directives/cdk-detail-row-directive';
import { ConfirmationDialogComponent } from './shared/components/confirmation-dialog/confirmation-dialog.component';
import { environment } from 'src/environments/environment';
import { HttpMockRequestInterceptor } from './core/interceptors/http-interceptor/http-mock-request-interceptor';
import { HttpRequestInterceptor } from './core/interceptors/http-interceptor/http-request-interceptor';
import { LoginDialogComponent } from './core/login-dialog/login-dialog.component';
  

@NgModule({
  declarations: [
    CdkDetailRowDirective,
    AppComponent,
    WinningsChartComponent,
    GamesComponent,
    AddGameComponent,
    MenuComponent,
    HomeComponent,
    PromptDialogComponent,
    PlayersComponent,
    AddPlayerComponent,
    LoginDialogComponent,
    ConfirmationDialogComponent
  ],
  entryComponents: [
    PromptDialogComponent,
    LoginDialogComponent,
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
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: environment.mock ? HttpMockRequestInterceptor : HttpRequestInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
