import { Component, Input, OnInit } from '@angular/core';
import { Resource } from 'src/app/domain/resource';
import { Sale } from 'src/app/domain/sale';
import { SaleService } from 'src/app/services/sale.service';

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrl: './balance.component.scss',
})
export class BalanceComponent implements OnInit{

  @Input()
  public isGeneralBalance: boolean = true;
  
  public expenses: number = 0;
  public income: number = 0;
  public selectedResource?: Resource;

  constructor(
    private saleService: SaleService
  ){}

  ngOnInit(): void {
    this.calculateBalance();
  }

  public balance(): number {
    let balance = 0;
    if(this.income != null && this.expenses != null) {
      balance = this.income - this.expenses;
    }
    return balance;
  }

  public calculateBalance() {
    if(this.isGeneralBalance) {
      this.saleService.getSales().subscribe((sales: Sale[]) => {
        this.setBalanceValues(sales);
      });
    } else if(this.selectedResource != null) {
      this.saleService.getSalesByResourceId(this.selectedResource.id!).subscribe((sales: Sale[]) => {
        this.setBalanceValues(sales);
      });
    } else {
      this.setBalanceValues([]);
    }
  }

  private setBalanceValues(sales: Sale[]) {
    let expenses = 0;
    let income = 0;

    sales.forEach(sale => {
      if (sale.isSold) {
        income! += sale.cost;
      } else {
        expenses! += sale.cost;
      }
    })

    this.expenses = expenses;
    this.income = income;
  }
}
