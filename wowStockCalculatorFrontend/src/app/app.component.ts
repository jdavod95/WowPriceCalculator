import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Resource } from 'src/app/domain/resource';
import { ResourceService } from 'src/app/services/resource.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor() { }

  title = 'wowStockCalculatorFrontend';
}
