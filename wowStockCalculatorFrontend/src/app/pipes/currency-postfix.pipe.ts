import { Pipe, PipeTransform } from '@angular/core';
import { environment } from 'src/environments/environment';

@Pipe({
  name: 'currencyPostfix',
  standalone: true
})
export class CurrencyPostfixPipe implements PipeTransform {

  transform(number: number | undefined): string {
    return number == null 
      ? '' 
      : `${number} ${environment.currency}` ;
  }

}
