import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-paging-tool',
  templateUrl: './paging-tool.component.html',
  styleUrl: './paging-tool.component.scss'
})
export class PagingToolComponent {
  
  public currentPage: number = 1;
  public pageCount: number = 1;

  @Input()
  public paginator!: MatPaginator;
  @Output()
  public outOfPages = new EventEmitter<number>()

  public onRight() {
    if(this.paginator.hasNextPage()) {
      this.paginator.nextPage()
      this.currentPage = this.paginator.pageIndex;
    } else if (this.paginator.pageIndex + 1 < this.pageCount) {
      this.outOfPages.emit(this.paginator.pageIndex);
    }
  }
  
  public onLeft() {
    this.paginator.previousPage()
    this.currentPage = this.paginator.pageIndex;
  }

  private reset() {
    this.currentPage = 0;
    this.paginator.firstPage();
  }

  public refresh(responseDataLength: number){
    this.paginator.length = responseDataLength;
    this.currentPage = this.paginator?.pageIndex!;
  }

  public onDatasourceReplaced(responseDataLength: number, totalElements: number){
    this.refresh(responseDataLength);
    this.reset();
    this.pageCount = Math.ceil(totalElements / environment.tablePageSize);
  }

  public onDatasourceExtended(responseDataLength: number){
    this.refresh(responseDataLength);
    this.onRight();
  }
}
