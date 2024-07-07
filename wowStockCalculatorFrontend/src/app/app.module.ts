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
import { StatisticsComponent } from './components/statistics/statistics.component';
import { ResourceFormComponent } from './components/resource-form/resource-form.component';
import { ResourcesComponent } from './components/resources/resources.component';
import { SaleFormComponent } from './components/sale-form/sale-form.component';
import { SalesComponent } from './components/sales/sales.component';

@NgModule({
  declarations: [
    AppComponent,
    StatisticsComponent,
    ResourceFormComponent,
    ResourcesComponent,
    SaleFormComponent,
    SalesComponent
  ],
  imports: [
    BrowserAnimationsModule,
    MaterialsModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [
    provideHttpClient(withFetch()),
    ResourceService,
    SaleService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
