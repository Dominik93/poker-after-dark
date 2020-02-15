import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';
import { MenuComponent } from './menu/menu.component';
import { HomeComponent } from './home/home.component';
import { PromptDialogComponent } from '../shared/components/prompt-dialog/prompt-dialog.component';
import { LoginDialogComponent } from './login-dialog/login-dialog.component';
import { ConfirmationDialogComponent } from '../shared/components/confirmation-dialog/confirmation-dialog.component';
import { MaterialModule } from '../material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { CoreRoutingModule } from './core-routing.module';
import { CdkDetailRowDirective } from '../shared/directives/cdk-detail-row-directive';
import { ChartComponent } from '../shared/components/chart/chart.component';

@NgModule({
  declarations: [
    MenuComponent,
    HomeComponent,
    ChartComponent,
    CdkDetailRowDirective,
    PromptDialogComponent,
    LoginDialogComponent,
    ConfirmationDialogComponent
  ],
  entryComponents: [
    PromptDialogComponent,
    LoginDialogComponent,
    ConfirmationDialogComponent
  ],
  exports: [
    MenuComponent,
    HomeComponent,
    ChartComponent,
    CdkDetailRowDirective,
    PromptDialogComponent,
    LoginDialogComponent,
    ConfirmationDialogComponent
  ],
  imports: [
    CoreRoutingModule,
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
export class CoreModule { }
