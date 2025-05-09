import { AbstractControl, ValidatorFn } from '@angular/forms';

export function yearValidator(minYear: number, maxYear: number): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    const year = Number(control.value);

    if (isNaN(year)) {
      return { 'invalidYear': { value: control.value } };
    }

    if (year < minYear || year > maxYear) {
      return { 'yearOutOfRange': { value: control.value } };
    }

    return null;
  };
}