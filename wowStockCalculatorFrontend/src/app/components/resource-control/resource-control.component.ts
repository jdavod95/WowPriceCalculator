import { Component, OnInit, ViewChild } from '@angular/core';
import { SalesComponent } from '../sales/sales.component';
import { Resource } from 'src/app/domain/resource';

@Component({
  selector: 'app-resource-control',
  templateUrl: './resource-control.component.html',
  styleUrl: './resource-control.component.scss'
})
export class ResourceControlComponent implements OnInit {
  @ViewChild(SalesComponent) salesComponent!: SalesComponent;
  
  public onResourceSelected(resource: Resource) {
    this.salesComponent.setSelectedResource(resource);
  }

  ngOnInit(): void {
  }

}
