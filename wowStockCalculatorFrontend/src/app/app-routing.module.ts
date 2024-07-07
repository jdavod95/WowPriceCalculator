import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StatisticsComponent } from './components/statistics/statistics.component';
import { ResourcesComponent } from './components/resources/resources.component';
import { SalesComponent } from './components/sales/sales.component';

const routes: Routes = [
  { path: '', component: StatisticsComponent },
  { path: 'resources', component: ResourcesComponent },
  { path: 'sales', component: SalesComponent },
  { path: 'statistics', component: StatisticsComponent },
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
