import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';
import { environment } from 'src/environments/environment';

@Pipe({
  name: 'dateTruncate',
  standalone: true
})
export class DateTruncatePipe implements PipeTransform {

  constructor(
    private datePipe: DatePipe
  ){}

  transform(date: Date): string | null {
    return this.datePipe.transform(date, environment.dateFormat);
  }

}
