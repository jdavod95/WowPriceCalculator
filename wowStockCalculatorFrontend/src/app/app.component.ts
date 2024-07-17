import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  //TODO: field validation, resource constraints, 
  // table width fix, filter by resource from sales
  // pagination, https policy, 
  // addresses to env variables or parameters
  // users & auth
  
  constructor() { }

  public appTitle = environment.appTitle;
}
