import { Component, ViewChild } from '@angular/core';
import { RecipesComponent } from '../recipes/recipes.component';
import { RecipeFormComponent } from '../recipe-form/recipe-form.component';
import { Recipe } from 'src/app/domain/recipe';

@Component({
  selector: 'app-recipe-control',
  templateUrl: './recipe-control.component.html',
  styleUrl: './recipe-control.component.scss'
})
export class RecipeControlComponent {

  @ViewChild(RecipesComponent) recipesComponent!: RecipesComponent;
  @ViewChild(RecipeFormComponent) recipesFormComponent!: RecipeFormComponent;
  
  public selectedRecipe: Recipe | undefined;

  public onRecipeCreated() {
    this.recipesComponent.getRecipes();
  }

  public onRecipeSelected(recipe: Recipe) {
    this.selectedRecipe = recipe;
  }

  public onRecipeUnSelected() {
    this.selectedRecipe = undefined;
    this.recipesComponent.selectedRecipe = undefined;
  }

}
