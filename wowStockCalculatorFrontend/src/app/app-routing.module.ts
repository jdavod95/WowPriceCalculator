import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RecipeControlComponent } from './components/recipes/recipe-control/recipe-control.component';
import { ResourceControlComponent } from './components/resources/resource-control/resource-control.component';

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
