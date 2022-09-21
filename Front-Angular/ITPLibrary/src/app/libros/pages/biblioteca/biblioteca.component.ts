import { Component, OnInit } from '@angular/core';
import { LibrosService } from '../../services/libros.service';
import { Libro } from '../../interfaces/libro.interface';

@Component({
  selector: 'app-biblioteca',
  templateUrl: './biblioteca.component.html',
  styleUrls: ['./biblioteca.component.scss']
})
export class BibliotecaComponent implements OnInit {
 
  termino: string = '';
  hayError: boolean = false;
  idioma : string = "";
 libros : Libro[] = []

  constructor(private LibrosService : LibrosService) { }

  
  buscar(termino: string){
    console.log("iniciando buskeda")
    this.hayError = false;
    this.termino = termino;
    this.LibrosService.buscarLibros(this.termino)
      .subscribe( (libros) => {
        this.libros = libros;
        console.log("Query OK");
        if (libros.length === 0) {
          this.hayError = true;
        }
      }, (err) => {
        this.hayError = true;
        this.libros = [];
        console.log("error")
      })
  }
  ngOnInit(): void {
  }

}
