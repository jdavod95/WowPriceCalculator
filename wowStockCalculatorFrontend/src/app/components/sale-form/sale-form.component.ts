import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Sale } from 'src/app/domain/sale';
import { StockMapping } from 'src/app/domain/stock-mapping';
import { SaleService } from 'src/app/services/sale.service';
import { StockMappingService } from 'src/app/services/stock-mapping.service';

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
  public stockMappings!: StockMapping[];

  constructor(
    private saleService: SaleService,
    private stockMappingService: StockMappingService,
    private formBuilder: FormBuilder) {

    this.form = this.formBuilder.group({
      resources: [''],
      amount: ['', [Validators.required, Validators.min(1)]],
      cost: ['', [Validators.required, Validators.min(0)]],
      isSold: [false],
      stock: ['']
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
      stock: [this.form.controls['stock'].value]
    };

    this.saleService.addSale(sale, this.selectedResourceId)
      .subscribe((response: Sale) => {
        this.saleCreated.emit();
        this.getStockMappings();
    });
    
    this.form.reset();
    this.submitted = false;
  }

  public isResourceSelected(): boolean {
    return this.selectedResourceId != null;
  }

  public setSelectedResource(resourceId: number) {
    this.selectedResourceId = resourceId;
    this.getStockMappings();
  }

  public getStockMappings(){
    this.stockMappingService.getStocks(this.selectedResourceId).subscribe(
      response => {
        this.stockMappings = response;
        this.stockMappings = this.stockMappings.filter(stock => stock.amount > 0);
      }
    )
  }
}
