import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Sale } from 'src/app/domain/sale';
import { SaleService } from 'src/app/services/sale.service';
import { SaleFormComponent } from '../sale-form/sale-form.component';
import { Resource } from 'src/app/domain/resource';
import { ModalService } from 'src/app/services/modal.service';
import { ResourceNamePipe } from 'src/app/pipes/resource-name.pipe';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.scss']
})
export class SalesComponent implements OnInit {
  
  @ViewChild(MatSort, { static: true }) sort!: MatSort;
  @ViewChild(SaleFormComponent) saleFormComponent!: SaleFormComponent;

  @Output()
  public salesChange = new EventEmitter<any>()
  @Output()
  public resourceSelected = new EventEmitter<Resource>()

  public selectedResource: Resource | undefined;
  public displayedColumns: string[] = ['resource', 'amount', 'cost', 'date', 'delete']
  public salesDataSource = new MatTableDataSource<Sale>();
  
  constructor(
    private saleService: SaleService,
    private modalService: ModalService,
    private resourceNamePipe: ResourceNamePipe
  ) { }

  ngOnInit(): void {
    if(this.selectedResource != null) {
      this.getSalesByResourceId(this.selectedResource.id!);
    } else {
      this.getSales();
    }

    this.sort.disableClear = true;
    this.salesDataSource.sort = this.sort;
  }

  public onSaleCreated() {
    this.salesChange.emit();
    this.ngOnInit();
  }

  public deleteSale(sale: Sale) {
    this.modalService.confirmationModal(
      this.resourceNamePipe.transform(sale.resource!.name) + ", " 
        + sale.amount + " pieces, " 
        + sale.cost + "g",
      "Are you sure you want to DELETE this sale?",
      () => {
        this.saleService.deleteSale(sale.id!).subscribe((response: any) => {
          this.salesChange.emit();
          this.ngOnInit();
        })
      }
    );
  }

  public getSales(): void {
    this.saleService.getSales().subscribe(
      (response: Sale[]) => {
        this.salesDataSource.data = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }  

  public getSalesByResourceId(resourceId: number): void {
    this.saleService.getSalesByresourceId(resourceId).subscribe(
      (response: Sale[]) => {
        this.salesDataSource.data = response;
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

  public selectRow(datasource: Sale[], index: number) {
    let orderedDatasource = this.salesDataSource._orderData(datasource);
    
    this.setSelectedResource(orderedDatasource[index].resource!);
    this.resourceSelected.emit(this.selectedResource);
  }
}
