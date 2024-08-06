import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { PagingToolComponent } from '../../paging-tool/paging-tool.component';
import { MatTableDataSource } from '@angular/material/table';
import { Recipe } from 'src/app/domain/recipe';
import { environment } from 'src/environments/environment';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes-list.component.html',
  styleUrl: './recipes-list.component.scss'
})
export class RecipesComponent implements OnInit {
  @ViewChild(MatSort, { static: true }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator!: MatPaginator;
  @ViewChild(PagingToolComponent, { static: true }) pagingTool!: PagingToolComponent;

  @Output()
  public recipeSelected = new EventEmitter<Recipe>()
  
  public displayedColumns: string[] = ['name', 'difficulty', 'delete'];
  public recipesDataSource = new MatTableDataSource<Recipe>;
  public selectedRecipe: Recipe | undefined;
  
  constructor(
    private recipeService: RecipeService
  ){}

  ngOnInit(): void {
    this.getRecipes();
    this.initTable();
  }
  
  private initTable() {
    this.sort.disableClear = true;
    this.paginator.pageSize = environment.tablePageSize;
    this.recipesDataSource.sort = this.sort;
    this.recipesDataSource.paginator = this.paginator;
  }

  public onOutOfPages($event: number) {
    throw new Error('Method not implemented.');
  }
  
  public onClickRecipe(recipe: Recipe) {
    if(this.selectedRecipe?.id == recipe.id) {
      this.selectedRecipe = undefined;
      this.recipeSelected.emit(this.selectedRecipe);
    } else {
      this.recipeService.getRecipe(recipe.id!).subscribe(
        (response: Recipe) => {
          this.selectedRecipe = response;
          this.recipeSelected.emit(this.selectedRecipe);
      });
    }
  }

  public deleteRecipe(recipe: Recipe) {
    this.recipeService.deleteRecipe(recipe.id!).subscribe(
      () => {
        this.getRecipes();
        this.recipeSelected.emit(undefined);
      }
    );
  }

  public applyFilter(event: Event) {
    let filterValue = (event.target as HTMLInputElement).value;
    this.recipesDataSource.filter = filterValue.trim().toLowerCase();
  }

  public getRecipes(): void {
    this.recipeService.getRecipes().subscribe(
      (response: any) => {
        this.recipesDataSource.data = response;
      }
    );
  }

  public setSelectedRecipe(recipe: Recipe | undefined){
    this.selectedRecipe = recipe;
  }
}
