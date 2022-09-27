import { Component, EventEmitter, OnInit, Output } from '@angular/core';
@Component({
  selector: 'app-buscador',
  templateUrl: './buscador.component.html'
})
export class BuscadorComponent implements OnInit {

  
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