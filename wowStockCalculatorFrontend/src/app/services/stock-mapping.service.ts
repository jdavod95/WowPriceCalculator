import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { StockMapping } from '../domain/stock-mapping';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StockMappingService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getStocks(resourceId: number): Observable<StockMapping[]> {
    return this.http.get<StockMapping[]>(`${this.apiServerUrl}/stockMapping/${resourceId}`);
  }
}
