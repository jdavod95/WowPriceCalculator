import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  //TODO: 
  // recipes
  // calculations
  // excel import, 
  // ip addresses to env variables or parameters
  // pagination/custom table refactor to be more reusable,
  // users & auth
  // https policy, 
  
  public appTitle = environment.appTitle;

  constructor() { }
}
