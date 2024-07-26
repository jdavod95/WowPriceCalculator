import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Resource } from 'src/app/domain/resource';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class ResourceService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getResources(): Observable<Resource[]> {
    return this.http.get<Resource[]>(`${this.apiServerUrl}/resources`);
  }

  public getResource(resourceId: number): Observable<Resource> {
    return this.http.get<Resource>(`${this.apiServerUrl}/resources/${resourceId}`);
  }

  public addResource(resource: Resource): Observable<HttpResponse<any>> {
    return this.http.put<HttpResponse<any>>(`${this.apiServerUrl}/resources`, resource, {observe: 'response'});
  }

  public updateResource(resource: Resource): Observable<Resource> {
    return this.http.patch<Resource>(`${this.apiServerUrl}/resources/${resource.id}`, resource);
  }

  public deleteResource(resourceId: number): Observable<any> {
    return this.http.delete<void>(`${this.apiServerUrl}/resources/${resourceId}`);
  }

  // request x times of the shown amount, get more if paged further
  public getResourcesPaged(pageIndex: number | undefined): Observable<any> {
    if(pageIndex != null) {
      pageIndex = Math.floor(((pageIndex + 1) / environment.tablePageBufferSize));
    }

    return this.http.get<any>(
      `${this.apiServerUrl}/resourcesPaged` +
        `?size=${environment.tablePageSize * environment.tablePageBufferSize}` +
        `&page=${pageIndex || 0}`);
  }
  
}
