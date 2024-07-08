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

@NgModule({
  declarations: [
    AppComponent,
    ResourceControlComponent,
    ResourceFormComponent,
    ResourcesComponent,
    SaleFormComponent,
    SalesComponent,
    ConfirmModalComponent
  ],
  imports: [
    ResourceNamePipe,
    BrowserAnimationsModule,
    MaterialsModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [
    ResourceNamePipe,
    provideHttpClient(withFetch()),
    ResourceService,
    SaleService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
