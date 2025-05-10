import { Component, Input, OnInit, ViewChild  } from '@angular/core';
import { FormGroup, FormBuilder, NgForm, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { FlightService } from '../flight.service';
import { map, switchMap } from 'rxjs/operators';
import { Move, MoveID } from '../move';
import { of } from 'rxjs';
import { MoveFilter } from '../flight-filter';
import { ProductService } from 'src/app/product/product.service';
import { yearValidator } from 'src/app/validate';
import { monthValidator } from 'src/app//month-validator';

@Component({
  selector: 'app-flight-edit',
  templateUrl: './flight-edit.component.html',
  styles: [
    // todo: figure out how to make width dynamic
    'form { display: flex; flex-direction: column; min-width: 500px; }',
    'form > * { width: 100% }'
  ],

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
  @ViewChild('editForm2') 
  form!: NgForm;
  validMonths = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
  editForm = this.fb.group({
    year: new FormControl({value: '', disabled: true}, [Validators.required, yearValidator(1900, 2099)]),
    month: new FormControl({value: '', disabled: true}, [Validators.required, monthValidator(this.validMonths),
      Validators.pattern(/^([1-9]|1[0-2])$/), // Matches 01 to 12
      Validators.minLength(1),
      Validators.maxLength(2),
      //Validators.pattern(/^\d{2}$/) // Matches exactly two digits
    ]),
    amount: new FormControl({value: '', disabled: true}, Validators.required),
    description: new FormControl({value: '', disabled: true}, Validators.required),
    produto: new FormControl({value: '', disabled: true}, Validators.required),
    cosif: new FormControl({value: '', disabled: true}, Validators.required),
  });


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private flightService: FlightService,
    private productService: ProductService,
    private fb: FormBuilder) {
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
          this.feedback = {type: 'warning', message: 'Erro ao carregar'};
        }
      );

      this.productService.find().subscribe(
        (response : any) => {
          this.select = response;
        },
        (error : any) => {
          console.error('Erro carregando data:', error);
        }
      );

  }
 

  onSubmit(form: NgForm) {
    // Handle form submission
    console.log(this.editForm.value);
    // Submit logic for reactive forms can be handled here if needed
    this.onReset(form); // Optionally reset after submission
  }

  onReset(form: NgForm) {
    this.move.id.idProduct = '';
    this.move.id.idCosif = '';
    this.feedback = {};
    this.disableField()
    form.resetForm(); // Clears values and resets state
  }

  save() {
    if(this.editForm.invalid) {
      this.feedback = {type: 'warning', message: 'Formulário está inválido!'};
      return;
    }
    this.flightService.save(this.move).subscribe(
      move => {
        this.move = move;
        this.feedback = {type: 'success', message: 'Salvo com sucesso!'};
        this.search({month:'', year: ""} as MoveFilter);
        setTimeout(() => {
          this.onSubmit(this.form);
        }, 1000);
        
      },
      err => {
        this.feedback = {type: 'warning', message: 'Erro ao salvar : ' + err.error.message};
      }
    );

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

        }
      );
    }
  }

  enableField(): void {
    this.editForm.enable()
    this.active = true
   
   }

   search(moveFilter: MoveFilter): void {
    this.flightService.load(moveFilter);
   }

  disableField(): void {
  this.editForm.disable()
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
