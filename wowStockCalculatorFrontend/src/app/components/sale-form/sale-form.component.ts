import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Sale } from 'src/app/domain/sale';
import { SaleService } from 'src/app/services/sale.service';

@Component({
  selector: 'app-sale-form',
  templateUrl: './sale-form.component.html',
  styleUrls: ['./sale-form.component.css'],
})
export class SaleFormComponent implements OnInit {

  public form: FormGroup;
  public selectedResourceId!: number;
  
  @Output()
  public saleCreated = new EventEmitter<void>()

  constructor(
    private saleService: SaleService,
    private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      resources: [''],
      amount: Number,
      cost: Number,
      isSold: [false]
    });
  }

  ngOnInit(): void {
  }

  submit() {
    let sale: Sale = {
      amount: this.form.controls['amount'].value,
      cost: this.form.controls['cost'].value,
      isSold: this.form.controls['isSold'].value,
    };
    this.saleService.addSale(sale, this.selectedResourceId)
      .subscribe((response: Sale) => {
        this.saleCreated.emit();
    });
  }

  isResourceSelected(): boolean {
    return this.selectedResourceId != null;
  }
}
