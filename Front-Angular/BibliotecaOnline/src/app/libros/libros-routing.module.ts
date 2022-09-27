import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { BibliotecaComponent } from './pages/biblioteca/biblioteca.component';
import { LibroComponent } from './pages/libro/libro.component';

const routes : Routes=[

      {


        path: '',
        children: [

        {
          path: 'biblioteca',
          component: BibliotecaComponent
        },
        {
          path: 'libro/:id',
          component: LibroComponent
        },
        {
          path: '**',
          redirectTo: 'biblioteca'
        }

        ]



      }


]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class LibrosRoutingModule { }
