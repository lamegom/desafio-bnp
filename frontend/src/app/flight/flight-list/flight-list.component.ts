import { Component, OnDestroy, OnInit } from '@angular/core';
import { FlightService } from '../flight.service';
import { Move } from '../move';
import { MoveFilter } from '../flight-filter';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-flight',
  templateUrl: 'flight-list.component.html',
  styles: [
    'table { min-width: 850px }',
  ]
})
export class FlightListComponent implements  OnDestroy  {
  displayedColumns = ['month', 'year', 'release', 'idProduct', 'idCosif', 'description', 'dtMovement', 'user', 'amount'];
  selectedFlight!: Move;
  feedback: any = {};
  receivedData: any;
  dataSubscription: Subscription;


  get flightList(): Move[] {
    return this.flightService.flightList? this.flightService.flightList : [] as Move[];
  }

  constructor(private flightService: FlightService) {
    this.dataSubscription = this.flightService.currentData.subscribe(data => {
      this.receivedData = data;
      // If you want to update the flight list, update it in the service:
      this.flightService.flightList = data;
  });
  }
  
  ngOnInit() {
    this.search();
  }

   search(): void {
    this.flightService.load({} as MoveFilter);
  }

  ngOnDestroy(): void {
  this.dataSubscription.unsubscribe();
}
}



