import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Libro } from '../../interfaces/libro.interface';
import { LibrosService } from '../../services/libros.service';

@Component({
  selector: 'app-libro',
  templateUrl: './libro.component.html',
  styleUrls: ['./libro.component.scss']
})
export class LibroComponent implements OnInit {

libro!: Libro; //El libro puede ser un objeto vacío, pero no puede ser undefined 


  constructor(
    private activatedRoute: ActivatedRoute,
    private librosService: LibrosService
  ) { }

  ngOnInit(): void {
    
    this.activatedRoute.params.subscribe( ({id}) => {
      this.librosService.getLibroId(id)
        .subscribe( libro => {
          this.libro = libro;
        })
    })


  }

}
