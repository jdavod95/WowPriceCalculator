import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Resource } from 'src/app/domain/resource';
import { ResourceService } from 'src/app/services/resource.service';

@Component({
  selector: 'app-resources',
  templateUrl: './resources.component.html',
  styleUrls: ['./resources.component.css']
})
export class ResourcesComponent implements OnInit {

  public resources: Resource[] = [];
  public displayedColumns: string[] = ['name', 'onStock']
  
  @Output()
  public resourceSelected = new EventEmitter<Resource>()

  constructor(private resourceService: ResourceService) { }

  ngOnInit(): void {
    this.getResources();
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

  selectRow(datasource: Resource[], index: number) {
    this.resourceSelected.emit(datasource[index]);
  }
}
