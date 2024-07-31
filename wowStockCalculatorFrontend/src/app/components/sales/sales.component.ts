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
import { PagingToolComponent } from '../paging-tool/paging-tool.component';
import { MatPaginator } from '@angular/material/paginator';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.scss']
})
export class SalesComponent implements OnInit {
  
  @ViewChild(MatSort, { static: true }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(PagingToolComponent, { static: true }) pagingTool!: PagingToolComponent;
  @ViewChild(SaleFormComponent) saleFormComponent!: SaleFormComponent;

  @Output()
  public salesChange = new EventEmitter<any>()
  @Output()
  public resourceSelected = new EventEmitter<Resource>()

  public selectedResource: Resource | undefined;
  public displayedColumns: string[] = ['resource', 'amount', 'cost', 'date', 'delete']
  public salesDataSource = new MatTableDataSource<Sale>;
  
  constructor(
    private saleService: SaleService,
    private modalService: ModalService,
    private resourceNamePipe: ResourceNamePipe
  ) { }

  ngOnInit(): void {
    this.getSales();
    this.initTable();
  }

  private initTable() {
    this.sort.disableClear = true;
    this.paginator.pageSize = environment.tablePageSize;
    this.salesDataSource.sort = this.sort;
    this.salesDataSource.paginator = this.paginator;
  }

  public onSaleCreated() {
    this.salesChange.emit();
    this.getSales();
  }

  public onOutOfPages(pageIndex: number) {
    this.getSales(pageIndex);
  }

  public deleteSale(sale: Sale) {
    this.modalService.confirmationModal(
      this.resourceNamePipe.transform(sale.resource!.name) + ", " 
        + sale.amount + " pieces, " 
        + sale.cost + environment.currency,
      "Are you sure you want to DELETE this sale?",
      () => {
        this.saleService.deleteSale(sale.id!).subscribe((response: any) => {
          this.salesChange.emit();
          this.saleFormComponent.getStockMappings();
          this.getSales();
        })
      }
    );
  }

  public getSales(pageIndex?: number): void {
    let getFilteredSales;

    if(this.selectedResource != null) {
      getFilteredSales = () => this.saleService.getSalesByResourceIdPaged(
        this.selectedResource!.id!, pageIndex || 0); 
    } else {
      getFilteredSales = () => this.saleService.getSalesPaged(
        pageIndex || 0);
    }

    getFilteredSales().subscribe(
      (response: any) => {
        if(pageIndex == null || pageIndex == 0){
          this.salesDataSource.data = response.content;
          this.pagingTool.onDatasourceReplaced(
            this.salesDataSource.data.length, 
            response.totalElements);
        } else {
          this.salesDataSource.data = [...this.salesDataSource.data, ...response.content];
          this.pagingTool.onDatasourceExtended(this.salesDataSource.data.length);
        }
      }
    );
  }  

  public setSelectedResource(resource: Resource) {
    this.selectedResource = resource;
    this.saleFormComponent.setSelectedResource(resource?.id!);
    this.getSales();
  }

  public onClickResource(resource: Resource) {
    this.setSelectedResource(resource);
    this.resourceSelected.emit(this.selectedResource);
  }
}
