import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, FormControl, ValidatorFn, ReactiveFormsModule } from '@angular/forms';
import { Observable } from 'rxjs';
import { Resource } from 'src/app/domain/resource';
import { Sale } from 'src/app/domain/sale';
import { ResourceService } from 'src/app/services/resource.service';
import { SaleService } from 'src/app/services/sale.service';
import { SalesComponent } from '../sales/sales.component';

@Component({
  selector: 'app-sale-form',
  templateUrl: './sale-form.component.html',
  styleUrls: ['./sale-form.component.css'],
})
export class SaleFormComponent implements OnInit {


  public form: FormGroup;
  public resources: Resource[] = [];
  public newSaleControl = new FormControl('');
  @ViewChild(SalesComponent) salesComponent!: SalesComponent;

  constructor(
    private saleService: SaleService,
    private resourceService: ResourceService,
    private formBuilder: FormBuilder) {
    this.form = this.formBuilder.group({
      resources: [''],
      amount: Number,
      cost: Number,
      isSold: Boolean
    });

  }
  ngOnInit(): void {
    this.getResources();
  }

  public getResources(): void {
    this.resourceService.getResources().subscribe(
      (response: Resource[]) => {
        this.resources = response;
        this.form.controls['resources'].patchValue(this.resources[0]);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  submit() {
    let sale: Sale = {
      amount: this.form.controls['amount'].value,
      cost: this.form.controls['cost'].value,
      isSold: this.form.controls['isSold'].value,
    };
    this.saleService.addSale(sale, Number(this.form.controls['resources'].value))
      .subscribe((response: Sale) => {
        this.salesComponent.ngOnInit();
      }
      );
  }
}
