import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';
import { Libro } from '../../interfaces/libro.interface';
import { LibrosService } from '../../service/libro.service';


@Component({
  selector: 'app-biblioteca',
  templateUrl: './biblioteca.component.html',
  styleUrls: ['./biblioteca.component.css']
})
export class BibliotecaComponent implements OnInit {
  items: MenuItem[] = []
  
  
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
    this.items = [
    {
      label: 'Menú',
      icon: 'pi pi-desktop',
      items: [
        {
          label: 'Inicio',
          icon: 'pi pi-home',
          routerLink: '/'
        },
        {
          label: 'Cátalogo Biblioteca',
          icon: 'pi pi-book',
          routerLink: 'biblioteca'
        }]
  },
  {
    label: 'Mi Usuario',
    icon: 'pi pi-user',
    items: [
      {
        label: 'Iniciar Sesion',
        icon: 'pi pi-sign-in',
        routerLink: 'login'
      },
      {
        label: 'Registrarse',
        icon: 'pi pi-user-plus',
        routerLink: 'registro'
      }
    ]
  }
  
    ];

  }
}
