import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ResourceControlComponent } from './components/resource-control/resource-control.component';
import { ResourcesComponent } from './components/resources/resources.component';
import { SalesComponent } from './components/sales/sales.component';

const routes: Routes = [
  { path: '', component: ResourceControlComponent },
  { path: 'resources', component: ResourcesComponent },
  { path: 'sales', component: SalesComponent },
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
