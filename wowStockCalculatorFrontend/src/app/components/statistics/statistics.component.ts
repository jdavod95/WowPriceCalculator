import { Component, OnInit, ViewChild } from '@angular/core';
import { SalesComponent } from '../sales/sales.component';
import { Resource } from 'src/app/domain/resource';

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrl: './statistics.component.css'
})
export class StatisticsComponent implements OnInit {
  @ViewChild(SalesComponent) salesComponent!: SalesComponent;
  
  public onResourceSelected(resource: Resource) {
    this.salesComponent.setSelectedResourceId(resource.id!);
  }

  ngOnInit(): void {
  }

}
