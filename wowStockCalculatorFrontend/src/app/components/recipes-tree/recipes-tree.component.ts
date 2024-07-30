import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Recipe } from 'src/app/domain/recipe';
import { RecipeService } from 'src/app/services/recipe.service';
import { Reagent } from 'src/app/domain/reagent';

@Component({
  selector: 'app-recipes-tree',
  templateUrl: './recipes-tree.component.html',
  styleUrl: './recipes-tree.component.scss'
})
export class RecipesTreeComponent implements OnInit {
  
  @Output()
  public recipeUnSelected = new EventEmitter<Recipe>()

  @Input()
  public selectedRecipe: Recipe | undefined;
  public recipesDataSource = new MatTableDataSource<Recipe | Reagent>;
  public displayedColumns: string[] = ['name', 'amount', 'onStock'];
  
  constructor(
    private recipeService: RecipeService
  ){}
  
  ngOnInit(): void {
    this.getRecipe();
  }

  
  public getRecipe(): void {
    this.recipeService.getRecipe(this.selectedRecipe?.id!).subscribe(
      (response: Recipe) => {
        console.log(response)
        this.recipesDataSource.data = [response, ...response.requiredReagents!, ...response.resultReagents! ];
        console.log(this.recipesDataSource)
        console.log(this.recipesDataSource.data)
      }
    );
  }

  public onRecipeUnSelected() {
    this.recipeUnSelected.emit();
  }
}
