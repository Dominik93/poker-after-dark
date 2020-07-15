import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { MaterialModule } from './material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PromptDialogComponent } from './shared/components/prompt-dialog/prompt-dialog.component';
import { MatNativeDateModule } from '@angular/material';
import { ConfirmationDialogComponent } from './shared/components/confirmation-dialog/confirmation-dialog.component';
import { environment } from 'src/environments/environment';
import { HttpMockRequestInterceptor } from './core/interceptors/http-interceptor/http-mock-request-interceptor';
import { HttpRequestInterceptor } from './core/interceptors/http-interceptor/http-request-interceptor';
import { LoginDialogComponent } from './core/login-dialog/login-dialog.component';
import { GameModule } from './modules/game/game-module';
import { PlayerModule } from './modules/player/player-module';
import { CoreModule } from './core/core.module';
import { WinningsChartModule } from './modules/winnings-chart/winnings-chart.module';
import { AppRoutingModule } from './app-routing.module';
import { CashGameModule } from './modules/cash-game/cash-game-module';
import { TournamentModule } from './modules/tournament/tournament-module';


@NgModule({
  declarations: [
    AppComponent
  ],
  entryComponents: [
    PromptDialogComponent,
    LoginDialogComponent,
    ConfirmationDialogComponent
  ],
  imports: [
    AppRoutingModule,
    CoreModule,
    GameModule,
    CashGameModule,
    TournamentModule,
    PlayerModule,
    WinningsChartModule,
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
