import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ResourceService } from 'src/app/services/resource.service';
import { SaleService } from './services/sale.service';
import { AppRoutingModule } from './app-routing.module';
import { provideHttpClient, withFetch} from '@angular/common/http'
import { MaterialsModule } from './materials.module';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ResourceControlComponent } from './components/resource-control/resource-control.component';
import { ResourceFormComponent } from './components/resource-form/resource-form.component';
import { ResourcesComponent } from './components/resources/resources.component';
import { SaleFormComponent } from './components/sale-form/sale-form.component';
import { SalesComponent } from './components/sales/sales.component';
import { ResourceNamePipe } from './pipes/resource-name.pipe';
import { ConfirmModalComponent } from './components/confirm-modal/confirm-modal.component';
import { CurrencyPostfixPipe } from './pipes/currency-postfix.pipe';
import { BalanceComponent } from './components/balance/balance.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { DatePipe } from '@angular/common';
import { DateTruncatePipe } from './pipes/date-truncate.pipe';
import { PositiveIntegerDirective } from './directives/positive-integer.directive';
import { PagingToolComponent } from './components/paging-tool/paging-tool.component';

@NgModule({
  declarations: [
    AppComponent,
    ResourceControlComponent,
    ResourceFormComponent,
    ResourcesComponent,
    SaleFormComponent,
    SalesComponent,
    ConfirmModalComponent,
    BalanceComponent,
    NavbarComponent,
    PagingToolComponent,
    PositiveIntegerDirective
  ],
  imports: [
    DatePipe,
    DateTruncatePipe,
    CurrencyPostfixPipe,
    ResourceNamePipe,
    BrowserAnimationsModule,
    MaterialsModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [
    DatePipe,
    DateTruncatePipe,
    CurrencyPostfixPipe,
    ResourceNamePipe,
    provideHttpClient(withFetch()),
    ResourceService,
    SaleService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
