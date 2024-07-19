import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Resource } from 'src/app/domain/resource';
import { ModalService } from 'src/app/services/modal.service';
import { ResourceService } from 'src/app/services/resource.service';
import { ResourceNamePipe } from 'src/app/pipes/resource-name.pipe';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { SaleService } from 'src/app/services/sale.service';
import { mapQuality } from 'src/app/domain/quality';
import { PagingToolComponent } from '../paging-tool/paging-tool.component';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.scss']
})
export class ResourcesComponent implements OnInit {

  
  public selectedResource: Resource | undefined;
  
  @ViewChild(MatSort, { static: true }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(PagingToolComponent, { static: true }) pagingTool!: PagingToolComponent;
  
  public displayedColumns: string[] = ['name', 'quality', 'onStock', 'delete']
  public resourcesDataSource = new MatTableDataSource<Resource>;
  
  @Output()
  public resourceSelected = new EventEmitter<Resource>()

  constructor(
    private resourceService: ResourceService,
    private saleService: SaleService,
    private modalService: ModalService,
    private resourceNamePipe: ResourceNamePipe
  ) {}

  ngOnInit(): void {
    this.getResources();
  }

  private initTable() {
    this.sort.disableClear = true;
    this.resourcesDataSource.sort = this.sort;
    
    this.paginator.pageSize = environment.tablePageSize;
    this.paginator.length = this.resourcesDataSource.data.length;

    this.resourcesDataSource.paginator = this.paginator;

    this.pagingTool.currentPage = this.resourcesDataSource.paginator?.pageIndex;
    this.pagingTool.pageCount = this.paginator.getNumberOfPages();
  }

  public onResourceCreated() {
    this.ngOnInit();
  }

  public getResources(): void {
    this.resourceService.getResources().subscribe(
      (response: Resource[]) => {
        response.forEach(resource => {
          resource.quality = mapQuality(resource.quality);          
        })
        this.resourcesDataSource.data = response;
        this.initTable();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public deleteResource(resource: Resource) {
    if(resource == null) {
      return;
    }

    let deleteResource = () => {
        this.resourceService.deleteResource(resource.id!).subscribe(() => {
          this.ngOnInit();
          this.resourceSelected.emit(undefined);
        })
    }

    this.saleService.getFirstSaleByResourceId(resource.id!).subscribe((response: any) => {
      if(response.content.length == 0){
        deleteResource();
      } else {
        this.modalService.confirmationModal(
          this.resourceNamePipe.transform(resource.name),
          "Are you sure you want to DELETE this resource? All associated sales will be deleted aswell!",
          deleteResource
        );
      }
    });
  }

  public applyFilter(event: Event) {
    let filterValue = (event.target as HTMLInputElement).value;
    this.resourcesDataSource.filter = filterValue.trim().toLowerCase();
  }

  public onClickResource(resource: Resource) {
    if(this.selectedResource?.id == resource.id) {
      this.selectedResource = undefined;
    } else {
      this.selectedResource = resource;
    }

    this.resourceSelected.emit(this.selectedResource);
  }
}
