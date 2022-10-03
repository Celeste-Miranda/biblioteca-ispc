import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProtectedRoutingModule } from './protected-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TablaLendingsComponent } from './components/tabla-lendings/tabla-lendings.component';
import {FieldsetModule} from 'primeng/fieldset';
import { PrimeNgModule } from '../prime-ng/prime-ng.module';

@NgModule({
  declarations: [
    DashboardComponent,
    TablaLendingsComponent
  ],
  imports: [
    CommonModule,
    ProtectedRoutingModule,
    FieldsetModule,
    PrimeNgModule
  ]
})
export class ProtectedModule { }
