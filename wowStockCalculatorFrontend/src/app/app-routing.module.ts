import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SaleFormComponent } from './components/sale-form/sale-form.component';
import { ResourceFormComponent } from './components/resource-form/resource-form.component';

const routes: Routes = [
  { path: '', component: SaleFormComponent },
  { path: 'resources', component: ResourceFormComponent },
  { path: 'sales', component: SaleFormComponent },
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
