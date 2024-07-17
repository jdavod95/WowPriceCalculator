import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Sale } from '../domain/sale';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  private apiServerUrl = environment.apiBaseUrl;
  
  constructor(private http: HttpClient) { }

  public getSales(): Observable<Sale[]> {
    return this.http.get<Sale[]>(`${this.apiServerUrl}/sales`);
  }
  
  public getSalesByresourceId(resourceId: number): Observable<Sale[]> {
    return this.http.get<Sale[]>(`${this.apiServerUrl}/sales/${resourceId}`);
  }

  public addSale(sale: Sale, resourceId: number): Observable<Sale> {
    return this.http.post<Sale>(`${this.apiServerUrl}/sales/${resourceId}`, sale);
  }

  public deleteSale(saleId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/sales/${saleId}`);
  }

  public getFirstSaleByResourceId(resourceId: number): Observable<Sale> {
    return this.http.get<Sale>(`${this.apiServerUrl}/salesPaged/${resourceId}?size=1`);
  }
}
