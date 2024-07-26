import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Sale } from 'src/app/domain/sale';
import { SaleService } from 'src/app/services/sale.service';

@Component({
  selector: 'app-sale-form',
  templateUrl: './sale-form.component.html',
  styleUrls: ['./sale-form.component.scss'],
})
export class SaleFormComponent implements OnInit {

  public form: FormGroup;
  public selectedResourceId!: number;

  @Output()
  public saleCreated = new EventEmitter<void>()
  public submitted: boolean = false;

  constructor(
    private saleService: SaleService,
    private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      resources: [''],
      amount: ['', [Validators.required, Validators.min(1)]],
      cost: ['', [Validators.required, Validators.min(0)]],
      isSold: [false]
    });
  }

  ngOnInit(): void {
  }

  get amountField() {
    return this.form.controls['amount'];
  }

  get costField() {
    return this.form.controls['cost'];
  }

  public submit() {
    this.submitted = true;
    if(this.form.invalid) {
      return;
    }

    let sale: Sale = {
      amount: parseInt(this.form.controls['amount'].value),
      cost: parseInt(this.form.controls['cost'].value),
      isSold: this.form.controls['isSold'].value,
    };

    this.saleService.addSale(sale, this.selectedResourceId)
      .subscribe((response: Sale) => {
        this.saleCreated.emit();
    });
    
    this.form.reset();
    this.submitted = false;
  }

  isResourceSelected(): boolean {
    return this.selectedResourceId != null;
  }
}
