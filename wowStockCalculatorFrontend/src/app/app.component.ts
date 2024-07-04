import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Resource } from 'src/domain/resource';
import { ResourceService } from 'src/services/resource.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public resources: Resource[] = [];

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
  title = 'wowStockCalculatorFrontend';
}
