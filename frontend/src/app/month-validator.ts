import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function monthValidator(validMonths: number[]): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const selectedMonth = control.value ? new Date(control.value).getMonth() + 1 : null; 

     if (isNaN(control.value)) {
            return { invalidMonth: true };
    }
    if (selectedMonth === null || validMonths.includes(selectedMonth)) {
      return null; 
    } else {
      return { invalidMonth: true };
    }


  };
}