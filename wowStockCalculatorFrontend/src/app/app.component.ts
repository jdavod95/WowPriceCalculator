import { Component } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  //TODO: 
  // pagination, https policy, 
  // ip addresses to env variables or parameters
  // users & auth
  
  constructor() { }

  public appTitle = environment.appTitle;
}
