import { Injectable } from '@angular/core';
import { BehaviorSubject, EMPTY, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Move, MoveID } from './move';
import { MoveFilter } from './flight-filter';

@Injectable()
export class FlightService {
  flightList: Move[] = [];

  constructor(private http: HttpClient) {
  }

  private data = new BehaviorSubject<any>(null);
  currentData = this.data.asObservable();

  setData(newData: any) {
    this.data.next(this.flightList);
  }

  getData() {
    return this.data.value;
  }

  load(moveFilter: MoveFilter): void {
    
 
    this.find(moveFilter).subscribe(result => {
      return  this.flightList = result.map(move => ({
          id: {
            month: move.id.month,
            year: move.id.year,
            release: move.id.release,
            idProduct: move.id.idProduct,
            idCosif: move.id.idCosif
        } as MoveID,
          description: move.description,
          dtMovement: move.dtMovement,
          user: move.user,
          amount: move.amount
        } as Move));

      },
      err => {
        console.error('error loading', err);
      }
    );
  }

  find(moveFilter: MoveFilter ): Observable<Move[]> {

    const year = moveFilter.year? moveFilter.year : null;
    const month = moveFilter.month? moveFilter.month : null;
    let url = `http://localhost:8080/movement-service/v1/moves/${year}/${month}`;

  if(!year || !month) {
    url = `http://localhost:8080/movement-service/v1/moves`;
  }

  const headers = new HttpHeaders().set('Accept', 'application/json');
  const params = {} as HttpParams;
  console.log("url: " + JSON.stringify(url));
  return this.http.get<Move[]>(url, {params, headers});
}

  save(entity: Move): Observable<Move> {
    console.log("entity: " + JSON.stringify(entity));
    let params = new HttpParams();
    let url = '';
    const headers = new HttpHeaders().set('content-type', 'application/json');
   // if (entity.id) {
    //  url = `http://www.angular.at/api/flight/${entity.id.toString()}`;
    //  params = new HttpParams().set('ID', entity.id.toString());
     // return this.http.put<Move>(url, entity, {headers, params});
    //} else {
      url = `http://localhost:8080/movement-service/v1/moves`;
      return this.http.post<Move>(url, entity, {headers, params});
   // }
  }


}

