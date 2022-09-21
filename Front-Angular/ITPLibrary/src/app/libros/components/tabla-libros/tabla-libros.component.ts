import { createInjectableDefinitionMap } from '@angular/compiler/src/render3/partial/injectable';
import { Component, OnInit } from '@angular/core';
import { Libro } from '../../interfaces/libro.interface';

@Component({
  selector: 'app-tabla-libros',
  templateUrl: './tabla-libros.component.html',
  styleUrls: ['./tabla-libros.component.scss']
})
export class TablaLibrosComponent implements OnInit {
  idioma : string = "";
  biblioteca : Libro[] = [
    {
      id: 1,
      title: "El señor de los anillos",
      date: "1954",
      category: "Fantasía",
      edit: "Minotauro",
      lang: "Español",
      pages: 1216,
      author: "J.R.R. Tolkien",
      description: "El señor de los anillos es una novela épica de fantasía escrita por el filólogo y académico británico J. R. R. Tolkien. Es la primera parte de la trilogía de El señor de los anillos, que consta de tres volúmenes: El señor de los anillos, Las dos torres y El retorno del rey. La novela narra la historia de un grupo de héroes que emprenden un viaje para destruir el Anillo Único, un artefacto de poder inimaginable que fue creado por el malvado Señor Oscuro Sauron con la intención de dominar a todos los pueblos de la Tierra Media.",
      ejemplares: 5,
      stock: 3,
      available: 2,
      img: "https://www.planetadelibros.com.ar/usuaris/libros/fotos/137/m_libros/portada_el-senor-de-los-anillos-i-la-comunidad-del-anillo_j-r-r-tolkien_202004261959.jpg"
    },
    {
      id: 2,
      title: "El hobbit",
      date: "1937",
      category: "Fantasía",
      edit: "Minotauro",
      lang: "Inglés",
      pages: 320,
      author: "J.R.R. Tolkien",
      description: "El Hobbit es una novela fantástica escrita por el filólogo y académico británico J. R. R. Tolkien. Es la primera obra de la Tierra Media, el universo ficticio en el que se desarrollan las novelas El señor de los anillos y El Silmarillion. La novela narra las aventuras de Bilbo Bolsón, un hobbit que emprende un viaje junto a trece enanos para recuperar el tesoro que les fue robado por el dragón Smaug.",
      ejemplares: 5,
      stock: 3,
      available: 0,
      img: "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/2611/9780261102217.jpg"
    }
  ];

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


  ngOnInit(): void {
  }

}
