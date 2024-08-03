import { Component, OnInit, ViewChild } from '@angular/core';
import { RecipesComponent } from '../recipes-list/recipes-list.component';
import { RecipeFormComponent } from '../recipe-form/recipe-form.component';
import { Recipe } from 'src/app/domain/recipe';
import { RecipeResultsComponent } from '../recipe-results/recipe-results.component';

@Component({
  selector: 'app-recipe-control',
  templateUrl: './recipe-control.component.html',
  styleUrl: './recipe-control.component.scss'
})
export class RecipeControlComponent implements OnInit {

  @ViewChild(RecipesComponent) recipesComponent!: RecipesComponent;
  @ViewChild(RecipeFormComponent) recipesFormComponent!: RecipeFormComponent;
  @ViewChild(RecipeResultsComponent) recipesResultsComponent!: RecipeResultsComponent;
  
  public selectedRecipe: Recipe | undefined;

  ngOnInit(): void {
  }

  public onRecipeCreated() {
    this.recipesComponent.getRecipes();
  }

  public onRecipeSelected(recipe: Recipe) {
    this.selectedRecipe = recipe;
  }

  public onRecipeUnSelected() {
    this.selectedRecipe = undefined;
  }

}
