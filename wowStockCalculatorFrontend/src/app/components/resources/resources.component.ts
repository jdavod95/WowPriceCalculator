import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Resource } from 'src/app/domain/resource';
import { ModalService } from 'src/app/services/modal.service';
import { ResourceService } from 'src/app/services/resource.service';
import { ResourceNamePipe } from 'src/app/pipes/resource-name.pipe';

@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.scss']
})
export class ResourcesComponent implements OnInit {

  public resources: Resource[] = [];
  public displayedColumns: string[] = ['name', 'onStock', 'delete']
  private selectedResource: Resource | undefined;
  
  @Output()
  public resourceSelected = new EventEmitter<Resource>()

  constructor(
    private resourceService: ResourceService,
    private modalService: ModalService,
    private resourceNamePipe: ResourceNamePipe
  ) {}

  ngOnInit(): void {
    this.getResources();
  }

  public onResourceCreated() {
    this.ngOnInit();
  }

  public getResources(): void {
    this.resourceService.getResources().subscribe(
      (response: Resource[]) => {
        this.resources = response;
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

  public selectRow(datasource: Resource[], index: number) {
    if(this.selectedResource == datasource[index]) {
      this.selectedResource = undefined;
    } else {
      this.selectedResource = datasource[index];
    }
    this.resourceSelected.emit(this.selectedResource);
  }
}
