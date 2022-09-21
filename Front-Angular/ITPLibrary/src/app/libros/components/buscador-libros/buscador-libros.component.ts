import { Component, EventEmitter, Output, Input, OnInit} from '@angular/core';
import { debounceTime, Subject } from 'rxjs';


@Component({
  selector: 'app-buscador-libros',
  templateUrl: './buscador-libros.component.html',
  styleUrls: ['./buscador-libros.component.scss']
})
export class BuscadorLibrosComponent implements OnInit {

  @Output() onEnter: EventEmitter<string> = new EventEmitter(); // Evento de salida para que dispare la busqueda hacia el componente buscador
  termino: string = '';

  constructor() { }

  ngOnInit(): void {
  }


  buscar(): void {
    this.onEnter.emit(this.termino); // Emitimos el evento de salida
    console.log(this.termino);
  }
}
