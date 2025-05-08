import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { FlightService } from '../flight.service';
import { map, switchMap } from 'rxjs/operators';
import { Move, MoveID } from '../move';
import { of } from 'rxjs';
import { MoveFilter } from '../flight-filter';
import { ProductService } from 'src/app/product/product.service';


@Component({
  selector: 'app-flight-edit',
  templateUrl: './flight-edit.component.html',
  styles: [
    // todo: figure out how to make width dynamic
    'form { display: flex; flex-direction: column; min-width: 500px; }',
    'form > * { width: 100% }'
  ]
})
export class FlightEditComponent implements OnInit {
  id!: string;
  move!: Move
  feedback: any = {};
  active: boolean = false;
cosif: { pk: { id: string; idCosif: string; };
classifier: string, 
status: string
        }[] = [];

  select!: { produto: {
                        id: string, 
                        descricao: string
                      },
            cosif: {
                        id: string, 
                        descricao: string
                      }[]; 
}[];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private flightService: FlightService,
  private productService: ProductService) {
  }

  ngOnInit() {
    this
      .route
      .params
      .pipe(
        map(p => p.id),
        switchMap(id => {
          return of({id:{} as MoveID} as Move);
        })
      )
      .subscribe(move => {
          this.move = move;
          this.feedback = {};
        },
        err => {
          this.feedback = {type: 'warning', message: 'Error loading'};
        }
      );

      this.productService.find().subscribe(
        (response : any) => {
          this.select = response;
        },
        (error : any) => {
          console.error('Error fetching data:', error);
        }
      );


  }

  save() {
    this.flightService.save(this.move).subscribe(
      move => {
        this.move = move;
        this.feedback = {type: 'success', message: 'Save was successful!'};
        this.search({month:'', year: ""} as MoveFilter);
      },
      err => {
        this.feedback = {type: 'warning', message: 'Error saving : ' }
      }
    );

    console.log("feedbak: " + this.feedback);
  }

  cancel() {
    
    this.router.navigate(['/flights']);
  }


  onChangeProduct(event: Event) {
    const productId = (event.target as HTMLInputElement).value;
    if (productId) {
      this.productService.getCosifs(productId).subscribe(
        (data: any) => {
          this.cosif = data as { pk: { id: string; idCosif: string; };
          classifier: string, 
          status: string
                  }[];

          console.log("cosif: " + JSON.stringify(this.cosif));
        }
      );
    }
  }

  enableField(): void {
    this.active = true
   
   }

   search(moveFilter: MoveFilter): void {
    this.flightService.load(moveFilter);
   }

  disableField(): void {

    this.active = false;

  }

  callFunctionAndStoreValue() {
    const result = this.search({month: this.move.id.month, year: this.move.id.year});
    this.flightService.setData(result);
  }

   sendData() {
    this.callFunctionAndStoreValue();
   }
  
}
