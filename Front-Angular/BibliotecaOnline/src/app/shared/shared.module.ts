import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { PrimeNgModule } from '../prime-ng/prime-ng.module';
import { MenubarModule } from 'primeng/menubar';



@NgModule({
  declarations: [
    NavbarComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    PrimeNgModule
  ],
  exports: [
    NavbarComponent,
    HomeComponent
  ]
})
export class SharedModule { }
