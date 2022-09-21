import { createInjectableDefinitionMap } from '@angular/compiler/src/render3/partial/injectable';
import { Component, Input } from '@angular/core';
import { Libro } from '../../interfaces/libro.interface';
import { LibrosService } from '../../services/libros.service';

@Component({
  selector: 'app-tabla-libros',
  templateUrl: './tabla-libros.component.html',
  styleUrls: ['./tabla-libros.component.scss']
})
export class TablaLibrosComponent{
  @Input() libros: Libro[] = [];

  constructor() { }



  determinarPais(libro: Libro): string {
   let bandera: string = "";

    if (libro.lang === 'Español') {
       bandera = "flag flag-spain"
    } else if (libro.lang === 'Inglés') {
        bandera = "flag flag-us"
    } else if (libro.lang === 'Francés') {
        bandera = "flag flag-france"
    } 
    return bandera; 
  }




}
