import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Recipe } from '../domain/recipe';

@Injectable({
  providedIn: 'root'
})

export class RecipeService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.apiServerUrl}/recipes`);
  }

  public getRecipe(recipeId: number): Observable<Recipe> {
    return this.http.get<Recipe>(`${this.apiServerUrl}/recipes/${recipeId}`);
  }
  
  public addRecipe(recipe: Recipe): Observable<HttpResponse<any>> {
    return this.http.post<HttpResponse<any>>(`${this.apiServerUrl}/recipes`, recipe, {observe: 'response'});
  }

  public deleteRecipe(recipeId: number): Observable<any> {
    return this.http.delete<void>(`${this.apiServerUrl}/recipes/${recipeId}`);
  }
}
