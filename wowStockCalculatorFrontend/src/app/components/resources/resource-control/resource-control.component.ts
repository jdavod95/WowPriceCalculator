import { Component, OnInit, ViewChild } from '@angular/core';
import { Resource } from 'src/app/domain/resource';
import { SalesComponent } from '../sales-list/sales-list.component';
import { ResourcesComponent } from '../resources-list/resources-list.component';
import { BalanceComponent } from '../../balance/balance.component';

@Component({
  selector: 'app-resource-control',
  templateUrl: './resource-control.component.html',
  styleUrl: './resource-control.component.scss'
})
export class ResourceControlComponent implements OnInit {
  
  @ViewChild(SalesComponent) salesComponent!: SalesComponent;
  @ViewChild(ResourcesComponent) resourcesComponent!: ResourcesComponent;
  @ViewChild('generalBalance') generalBalance!: BalanceComponent;
  @ViewChild('resourceBalance') resourceBalance!: BalanceComponent;

  constructor(){}

  ngOnInit(): void {
  }

  public onResourceSelected(resource: Resource) {
    this.resourceBalance.selectedResource = resource;
    this.resourceBalance.calculateBalance();
    this.salesComponent.setSelectedResource(resource);
    this.resourcesComponent.selectedResource = resource;
  }
  
  public onSalesChange() {
    this.generalBalance.calculateBalance();
    this.resourceBalance.calculateBalance();
    this.resourcesComponent.getResources();
  }
  
}
