import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from '@angular/forms';

import { TablaComponent } from './components/tabla/tabla.component';
import { BuscadorComponent } from './components/buscador/buscador.component';
import { BibliotecaComponent } from './pages/biblioteca/biblioteca.component';
import { LibroComponent } from './pages/libro/libro.component';
import { PrimeNgModule } from '../prime-ng/prime-ng.module';
import { HttpClientModule } from '@angular/common/http';
import { BanderaPipe } from './pipes/bandera.pipe';
import { DisponibilidadPipe } from './pipes/disponibilidad.pipe';
import { DisponibilidadTextPipe } from './pipes/disponibilidad-text.pipe';
import { LibrosRoutingModule } from './libros-routing.module';
import {ConfirmationService} from 'primeng/api';


@NgModule({
  declarations: [
    TablaComponent,
    BuscadorComponent,
    BibliotecaComponent,
    LibroComponent,
    BanderaPipe,
    DisponibilidadPipe,
    DisponibilidadTextPipe
  ],
  imports: [
    CommonModule,
    FormsModule,
    PrimeNgModule,
    LibrosRoutingModule
  ],
  exports: [
    HttpClientModule,
    TablaComponent,
    BuscadorComponent,
    BibliotecaComponent,
    LibroComponent
  ],
  providers: [
    ConfirmationService
  ]
})
export class LibrosModule { }
