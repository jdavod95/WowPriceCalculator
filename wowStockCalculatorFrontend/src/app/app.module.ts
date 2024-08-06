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
import { ResourcesComponent } from './components/resources/resources-list/resources-list.component';
import { SaleFormComponent } from './components/resources/sale-form/sale-form.component';
import { SalesComponent } from './components/resources/sales-list/sales-list.component';
import { ResourceNamePipe } from './pipes/resource-name.pipe';
import { ConfirmModalComponent } from './components/confirm-modal/confirm-modal.component';
import { CurrencyPostfixPipe } from './pipes/currency-postfix.pipe';
import { BalanceComponent } from './components/balance/balance.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { DatePipe } from '@angular/common';
import { DateTruncatePipe } from './pipes/date-truncate.pipe';
import { PositiveIntegerDirective } from './directives/positive-integer.directive';
import { PagingToolComponent } from './components/paging-tool/paging-tool.component';
import { RecipeControlComponent } from './components/recipes/recipe-control/recipe-control.component';
import { RecipesComponent } from './components/recipes/recipes-list/recipes-list.component';
import { RecipeFormComponent } from './components/recipes/recipe-form/recipe-form.component';
import { RecipeService } from './services/recipe.service';
import { ReagentService } from './services/reagent.service';
import { RecipesTreeComponent } from './components/recipes/recipes-tree/recipes-tree.component';
import { ResourceControlComponent } from './components/resources/resource-control/resource-control.component';
import { ResourceFormComponent } from './components/resources/resource-form/resource-form.component';
import { RecipeResultsComponent } from './components/recipes/recipe-results/recipe-results.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipeControlComponent,
    RecipesComponent,
    RecipesTreeComponent,
    RecipeFormComponent,
    RecipeResultsComponent,
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
    SaleService,
    RecipeService,
    ReagentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
