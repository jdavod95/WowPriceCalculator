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
  // pagination refactor to be more reusable,
  
  constructor() { }

  public appTitle = environment.appTitle;
}
