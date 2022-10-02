import { Component, Injectable, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})

@Injectable ({
  providedIn: 'root'
})

export class NavbarComponent implements OnInit {
  
 
nombre: string = '';
items: MenuItem[] = [];
  
  constructor(private authService: AuthService) { }
 


  ngOnInit(): void {
   
    this.items= [
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
            routerLink: 'libros/biblioteca'
          }]
    },
    {
      label: 'Cuenta',
      escape: false,
      icon: 'pi pi-user',
      items: [
        {
          label: 'Iniciar Sesion',
          icon: 'pi pi-sign-in',
          routerLink: 'auth/login'
        },
        {
          label: 'Registrarse',
          icon: 'pi pi-user-plus',
          routerLink: 'auth/register',
          command: (onClick) => {
           // this.authService.logout();

          }
        }
      ]
    }
  
    
  
  
      ];

   this.nombre = localStorage.getItem('username')!;
   console.log(this.nombre);
   if(this.nombre === null){
     this.items[1].label = "Cuenta"
  
   } else {
      this.items[1].label = this.nombre;
      this.items[1].items![0].label= 'Mi Cuenta';
      this.items[1].items![0].routerLink= '/dashboard';
      this.items[1].items![1].label= 'Cerrar Sesión';
      this.items[1].items![1].command = (onClick) => {this.authService.logout();};
      this.items[1].items![1].routerLink= '/auth';
      
   }

  
}




   
  }
  





