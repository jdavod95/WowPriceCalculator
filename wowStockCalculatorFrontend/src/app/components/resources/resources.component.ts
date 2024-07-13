import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Resource } from 'src/app/domain/resource';
import { ModalService } from 'src/app/services/modal.service';
import { ResourceService } from 'src/app/services/resource.service';
import { ResourceNamePipe } from 'src/app/pipes/resource-name.pipe';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.scss']
})
export class ResourcesComponent implements OnInit {
  
  private selectedResource: Resource | undefined;
  
  @ViewChild(MatSort, { static: true }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  
  public displayedColumns: string[] = ['name', 'onStock', 'delete']
  public resourcesDataSource = new MatTableDataSource<Resource>();
  
  @Output()
  public resourceSelected = new EventEmitter<Resource>()

  constructor(
    private resourceService: ResourceService,
    private modalService: ModalService,
    private resourceNamePipe: ResourceNamePipe
  ) {}

  ngOnInit(): void {
    this.getResources();
    this.sort.disableClear = true;
    this.resourcesDataSource.sort = this.sort;
    this.resourcesDataSource.paginator = this.paginator;
  }

  public onResourceCreated() {
    this.ngOnInit();
  }

  public getResources(): void {
    this.resourceService.getResources().subscribe(
      (response: Resource[]) => {
        this.resourcesDataSource.data = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public deleteResource(resource: Resource) {
    this.modalService.confirmationModal(
      this.resourceNamePipe.transform(resource.name),
      "Are you sure you want to DELETE this resource? All associated sales will be deleted aswell!",
      () => {
        this.resourceService.deleteResource(resource.id!).subscribe((response: any) => {
          this.ngOnInit();
          this.resourceSelected.emit(undefined);
        })
      }
    );
  }

  applyFilter(event: Event) {
    let filterValue = (event.target as HTMLInputElement).value;
    this.resourcesDataSource.filter = filterValue.trim().toLowerCase();
  }

  public selectRow(datasource: Resource[], index: number) {
    let orderedDatasource = this.resourcesDataSource._orderData(datasource);

    if(this.selectedResource == orderedDatasource[index]) {
      this.selectedResource = undefined;
    } else {
      this.selectedResource = orderedDatasource[index];
    }
    this.resourceSelected.emit(this.selectedResource);
  }
}
