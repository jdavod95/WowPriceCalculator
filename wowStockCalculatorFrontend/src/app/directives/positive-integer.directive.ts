import { Directive, HostListener } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[positiveInteger]'
})
export class PositiveIntegerDirective {

    private maxInteger = 2147483647;
    constructor(private control: NgControl) {}

    @HostListener('input', ['$event']) onInputChange(event: Event) {
        const input = event.target as HTMLInputElement;
        let value = input.value;

        if (value) {
        value = value.replace(/[^0-9]/g, '');
        
        if (parseInt(value, 10) < 1) {
            value = '';
        }

        if (parseInt(value) > this.maxInteger){
            value = this.maxInteger.toString();
        }
        
        this.control.control?.setValue(value);
        }
    }
}
