import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { FlightListComponent } from './flight-list/flight-list.component';
import { FlightEditComponent } from './flight-edit/flight-edit.component';
import { FlightService } from './flight.service';
import { FLIGHT_ROUTES } from './flight.routes';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';
import { ProductService } from '../product/product.service';
import { CurrencyMaskModule } from "ng2-currency-mask";
import { MatSelectModule } from '@angular/material/select';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild(FLIGHT_ROUTES),
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    CurrencyMaskModule,
    MatSelectModule
  ],
  declarations: [
    FlightListComponent,
    FlightEditComponent,
  ],
  providers: [FlightService,
    ProductService
  ],
  exports: [],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class FlightModule { }
