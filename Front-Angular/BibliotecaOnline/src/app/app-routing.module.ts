import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './shared/home/home.component';
import { BibliotecaComponent } from './libros/pages/biblioteca/biblioteca.component';
import { LibroComponent } from './libros/pages/libro/libro.component';
import { ErrorComponent } from './shared/error/error.component';
import { ValidarTokenGuard } from './guards/validar-token.guard';

const routes: Routes = [
  {
     path: 'auth',
     loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
  },
  {
     path: 'dashboard',
     loadChildren: () => import('./protected/protected.module').then(m => m.ProtectedModule),
     canActivate: [
      ValidarTokenGuard
     ]
       
  },
  {
    path: 'libros',
    loadChildren: () => import('./libros/libros.module').then(m => m.LibrosModule)
 },
  {
    path: '404',
    component: ErrorComponent
  },
  { 
    path: '',
    component: HomeComponent
  },
  {
   path: '**',    
   component: ErrorComponent
 }
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }