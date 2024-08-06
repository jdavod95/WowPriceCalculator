import { Component, Input, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Reagent } from 'src/app/domain/reagent';
import { Recipe } from 'src/app/domain/recipe';

@Component({
  selector: 'app-recipe-results',
  templateUrl: './recipe-results.component.html',
  styleUrl: './recipe-results.component.scss'
})
export class RecipeResultsComponent implements OnInit {
  
  @Input()
  public selectedRecipe: Recipe | undefined;
  public requiredReagentsSource = new MatTableDataSource<Reagent>;
  public displayedColumns: string[] = ['expectedUsage', 'expectedCost', 'actualUsage', 'actualCost'];
  public multiplier = 1;
  
  
  public ngOnInit(): void {
    console.log(this.selectedRecipe)
    if(this.selectedRecipe != null) {
      this.requiredReagentsSource.data = this.selectedRecipe!.requiredReagents!;
    }
  }

  public setMultiplier(event: Event) {
    this.multiplier = Number.parseInt((event.target as HTMLInputElement).value);
  }

  public setSelectedRecipe(recipe: Recipe | undefined){
    this.selectedRecipe = recipe;
  }
}
