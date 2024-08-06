import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Reagent } from '../domain/reagent';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReagentService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }
  
  public getReagents(): Observable<Reagent[]> {
    return this.http.get<Reagent[]>(`${this.apiServerUrl}/reagents`);
  }
}
