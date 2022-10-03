import { Component, OnInit, ResolvedReflectiveFactory } from '@angular/core';
import { ActivatedRoute, RouterLink, Router } from '@angular/router';
import { ConfirmationService } from 'primeng/api';
import { Libro } from '../../interfaces/libro.interface';
import { LibrosService } from '../../service/libro.service';
import { PrimeNGConfig } from 'primeng/api';
import {MessageService} from 'primeng/api';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ResponseBack } from '../../../auth/interfaces/responseBack.interface';

@Component({
  selector: 'app-libro',
  templateUrl: './libro.component.html',
  styleUrls: ['./libro.component.css']
})
export class LibroComponent implements OnInit {

  libro!: Libro; //El libro puede ser un objeto vacío, pero no puede ser undefined 
  rol: string = localStorage.getItem('role') || '';

   respuesta: number = 0;

  constructor(
    private activatedRoute: ActivatedRoute,
    private librosService: LibrosService,
   private confirmationService: ConfirmationService,
   private config: PrimeNGConfig,
  private router : Router, 
  private messageService: MessageService
  
  ) { }


  ngOnInit(): void {

    if(localStorage.getItem('role') == 'USUARIO'){
    
    this.config.setTranslation({
      accept: 'Reservar',
      reject: 'Cancelar',
      //translations
  });
    } else {
      this.config.setTranslation({
        accept: 'Ir a Login',
        reject: 'Salir',
        //translations
    });
    }

    this.activatedRoute.params.subscribe( ({id}) => {
      this.librosService.getLibroId(id)
        .subscribe( libro => {
          this.libro = libro;
        })
    })


  }



reservar(libroId : number) {
  this.librosService.userLending(libroId).subscribe ({
    next: data => {
      
      console.log(data.toString());
      this.messageService.add({severity:'success', summary: 'Reserva', detail: this.respuesta.toString()});
      //Por alguna razon, todo lo que recibe de este endpoint lo traduce como "Error" 
    },
    error: err => {
      this.respuesta = err.error.status;
      if(this.respuesta===400) { 
      this.messageService.add({severity:'error', summary: 'Reserva', detail: err.error.message});
      this.respuesta = 0;
      }
      else 
      this.messageService.add({severity:'success', summary: 'Reserva', detail: 'Se ha realizado la reserva con éxito'});
      this.respuesta = 0;
    }
  })
  
  
  

}


  confirm() {

    if(localStorage.getItem('role') == 'USUARIO'){
      this.confirmationService.confirm({
          message: 'Realizará la Reserva del Libro, ¿Desea Continuar?',
          accept: () => {
             this.reservar(this.libro.id);
          }
      });
  } else {
    this.confirmationService.confirm({
      message: 'Debe iniciar sesión para realizar la reserva del libro. Si no tiene cuenta, debe registrarse',
      accept: () => {
          this.router.navigateByUrl('/auth');
      }
  });
  }
}


}