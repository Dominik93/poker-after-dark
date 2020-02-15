import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';
import { WinningsChartComponent } from './components/winnings-chart.component';
import { PromptDialogComponent } from 'src/app/shared/components/prompt-dialog/prompt-dialog.component';
import { ConfirmationDialogComponent } from 'src/app/shared/components/confirmation-dialog/confirmation-dialog.component';
import { LoginDialogComponent } from 'src/app/core/login-dialog/login-dialog.component';
import { MaterialModule } from 'src/app/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { WinningsChartRoutingModule } from './winnings-chart-rounting.module';
import { ChartComponent } from 'src/app/shared/components/chart/chart.component';
import { CoreModule } from 'src/app/core/core.module';

@NgModule({
  declarations: [
    WinningsChartComponent
  ],
  entryComponents: [
    PromptDialogComponent,
    LoginDialogComponent,
    ConfirmationDialogComponent
  ],
  imports: [
    CoreModule,
    WinningsChartRoutingModule,
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
export class WinningsChartModule { }
