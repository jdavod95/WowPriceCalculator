import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Sale } from 'src/app/domain/sale';
import { SaleService } from 'src/app/services/sale.service';
import { SaleFormComponent } from '../sale-form/sale-form.component';
import { Resource } from 'src/app/domain/resource';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.scss']
})
export class SalesComponent implements OnInit {

  public sales: Sale[] = [];
  public displayedColumns: string[] = ['resource', 'amount', 'cost', 'date']
  @ViewChild(SaleFormComponent) saleFormComponent!: SaleFormComponent;
  
  public selectedResource: Resource | undefined;

  constructor(private saleService: SaleService) { }

  ngOnInit(): void {
    if(this.selectedResource != null) {
      this.getSalesByResourceId(this.selectedResource.id!);
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

  public setSelectedResource(resource: Resource) {
    this.selectedResource = resource;
    this.saleFormComponent.selectedResourceId = resource?.id!;
    this.ngOnInit();
  }
}
