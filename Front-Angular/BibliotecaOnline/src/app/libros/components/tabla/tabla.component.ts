import { Component, Input, OnInit } from '@angular/core';
import { Libro } from '../../interfaces/libro.interface';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html'
})
export class TablaComponent {

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