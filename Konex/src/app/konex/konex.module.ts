import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { IndexComponent } from './components/index/index.component';
import { MedicamentosComponent } from './components/medicamentos/medicamentos.component';
import { VentasComponent } from './components/ventas/ventas.component';
import { BrowserModule } from '@angular/platform-browser';
import { MedicamentoPipe } from '../filter/medicamento.filter.component';
import { HomeComponent } from './components/home/home.component';
import { DateFormatPipe } from '../filter/date-format.pipe';
import { DateTimeFormatPipe } from '../filter/datetime-format.pipe';


@NgModule({
  declarations: [
    MedicamentosComponent,
    VentasComponent,
    IndexComponent,
    MedicamentoPipe,
    DateFormatPipe,
    DateTimeFormatPipe,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    FormsModule, 
    ReactiveFormsModule
  ],
  providers: [DateFormatPipe, DateTimeFormatPipe],
  schemas: [NO_ERRORS_SCHEMA]
})
export class KonexAppModule { }
