import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TablaLibrosComponent } from './components/tabla-libros/tabla-libros.component';
import { BibliotecaComponent } from './pages/biblioteca/biblioteca.component';
import { FiltroLibrosComponent } from './components/filtro-libros/filtro-libros.component';
import { BuscadorLibrosComponent } from './components/buscador-libros/buscador-libros.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { LibroComponent } from './pages/libro/libro.component';



@NgModule({
  declarations: [
    TablaLibrosComponent,
    BibliotecaComponent,
    FiltroLibrosComponent,
    BuscadorLibrosComponent,
    LibroComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  exports: [
    TablaLibrosComponent,
    BibliotecaComponent,
    FiltroLibrosComponent,
    BuscadorLibrosComponent
  ]
})
export class LibrosModule { }
