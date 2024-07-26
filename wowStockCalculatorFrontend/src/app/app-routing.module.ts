import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ResourceControlComponent } from './components/resource-control/resource-control.component';
import { RecipeControlComponent } from './components/recipe-control/recipe-control.component';

const routes: Routes = [
  { path: '', component: RecipeControlComponent },
  { path: 'resources', component: ResourceControlComponent },
  { path: 'recipes', component: RecipeControlComponent },
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
