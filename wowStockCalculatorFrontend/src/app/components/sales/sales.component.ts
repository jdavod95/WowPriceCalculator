import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Sale } from 'src/app/domain/sale';
import { SaleService } from 'src/app/services/sale.service';
import { SaleFormComponent } from '../sale-form/sale-form.component';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})
export class SalesComponent implements OnInit {

  public sales: Sale[] = [];
  public displayedColumns: string[] = ['resource', 'amount', 'cost', 'date']
  @ViewChild(SaleFormComponent) saleFormComponent!: SaleFormComponent;
  
  private resourceId: number | undefined;

  constructor(private saleService: SaleService) { }

  ngOnInit(): void {
    if(this.resourceId != null) {
      this.getSalesByResourceId(this.resourceId);
    } else {
      this.getSales();
    }

  }

  public onSaleCreated() {
    this.ngOnInit();
  }
  
  public getSales(): void {
    this.saleService.getSales().subscribe(
      (response: Sale[]) => {
        this.sales = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }  

  public getSalesByResourceId(resourceId: number): void {
    this.saleService.getSalesByresourceId(resourceId).subscribe(
      (response: Sale[]) => {
        this.sales = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public setSelectedResourceId(resourceId: number) {
    this.resourceId = resourceId;
    this.saleFormComponent.selectedResourceId = resourceId;
    this.ngOnInit();
  }
}
