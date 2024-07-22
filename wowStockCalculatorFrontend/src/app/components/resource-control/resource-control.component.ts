import { Component, OnInit, ViewChild } from '@angular/core';
import { SalesComponent } from '../sales/sales.component';
import { Resource } from 'src/app/domain/resource';
import { ResourcesComponent } from '../resources/resources.component';
import { BalanceComponent } from '../balance/balance.component';

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
