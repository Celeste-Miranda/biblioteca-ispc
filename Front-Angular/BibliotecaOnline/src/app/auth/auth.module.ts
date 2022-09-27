import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms'


import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { AuthRoutingModule } from './auth-routing.module';
import { MainComponent } from './pages/main/main.component';
import { PrimeNgModule } from '../prime-ng/prime-ng.module';



@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    MainComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    PrimeNgModule,
    ReactiveFormsModule
  ]
})
export class AuthModule { }
