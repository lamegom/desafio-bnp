
import { Injectable } from '@angular/core';
import { EMPTY, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Move, MoveID } from '../flight/move';

interface MyData {
    produto: string;
    cosif: string;
  }

@Injectable()
export class ProductService {

  constructor(private http: HttpClient) {
  }



  find(): Observable<MyData[]> {
   
    const url = `http://localhost:8080/product-service/v1/products/select`;
    const headers = new HttpHeaders().set('Accept', 'application/json');

    const params = {};

    return this.http.get<MyData[]>(url, {params, headers});
  }

  getCosifs(productId: any) {
    return this.http.get(`http://localhost:8080/product-service/v1/products/${productId}`);
  }

}

