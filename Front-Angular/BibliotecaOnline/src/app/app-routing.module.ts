import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './shared/home/home.component';
import { BibliotecaComponent } from './libros/pages/biblioteca/biblioteca.component';
import { LibroComponent } from './libros/pages/libro/libro.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'biblioteca',
    component: BibliotecaComponent
  },
  {
    path: 'libro/:id',
    component: LibroComponent
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