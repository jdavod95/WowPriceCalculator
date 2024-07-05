import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ResourceService } from 'src/app/services/resource.service';
import { SaleService } from './services/sale.service';
import { ResourcesComponent } from './components/resources/resources.component';
import { SalesComponent } from './components/sales/sales.component';
import { AppRoutingModule } from './app-routing.module';
import { SaleFormComponent } from './components/sale-form/sale-form.component';
import { provideHttpClient, withFetch} from '@angular/common/http'
import { MaterialsModule } from './materials.module';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    ResourcesComponent,
    SalesComponent,
    SaleFormComponent
  ],
  imports: [
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
