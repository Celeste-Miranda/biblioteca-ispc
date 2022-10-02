import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProtectedRoutingModule } from './protected-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TablaLendingsComponent } from './components/tabla-lendings/tabla-lendings.component';


@NgModule({
  declarations: [
    DashboardComponent,
    TablaLendingsComponent
  ],
  imports: [
    CommonModule,
    ProtectedRoutingModule
  ]
})
export class ProtectedModule { }
