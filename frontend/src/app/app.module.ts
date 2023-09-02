import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ScheduleComponent } from './components/schedule/schedule.component';
import { SearchComponent } from './components/search/search.component';

import { ButtonModule } from 'primeng/button'
import { CalendarModule } from 'primeng/calendar';
import { ToastModule } from 'primeng/toast';
import { TableModule } from 'primeng/table';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MainComponent } from './components/main/main.component';

import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { HttpClientModule } from '@angular/common/http';
import { AppointmentService } from './service/appointment.service';


@NgModule({
  declarations: [
    AppComponent,
    ScheduleComponent,
    SearchComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ButtonModule,
    CalendarModule,
    BrowserAnimationsModule,
    InputTextModule,
    FormsModule,
    ToastModule,
    TableModule,
    HttpClientModule
  ],
  providers: [MessageService, AppointmentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
