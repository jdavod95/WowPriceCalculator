import { Component, Input } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-paging-tool',
  templateUrl: './paging-tool.component.html',
  styleUrl: './paging-tool.component.scss'
})
export class PagingToolComponent {
  
  public currentPage: number = 0;
  public pageCount: number = 1;
  
  @Input()
  public paginator!: MatPaginator;

  public onRight() {
    this.paginator.nextPage()
    this.currentPage = this.paginator.pageIndex;
  }
  
  public onLeft() {
    this.paginator.previousPage()
    this.currentPage = this.paginator.pageIndex;
  }
}
