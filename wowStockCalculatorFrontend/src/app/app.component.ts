import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  //TODO: 
  // https policy, 
  // ip addresses to env variables or parameters
  // users & auth
  // excel import, recipes
  // calculations
  // pagination/custom table refactor to be more reusable,
  
  public appTitle = environment.appTitle;

  constructor() { }
}
