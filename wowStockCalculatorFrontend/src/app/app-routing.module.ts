import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ResourceControlComponent } from './components/resource-control/resource-control.component';

const routes: Routes = [
  { path: '', component: ResourceControlComponent },
  { path: 'resources', component: ResourceControlComponent },
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
