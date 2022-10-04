import { Component, Injectable } from '@angular/core';
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

export class NavbarComponent {
  
 
nombre: string = '';

  constructor(private authService: AuthService) { }
 
  items: MenuItem[]= [
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

    itemsLogueado: MenuItem[]= [
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
      label: (this.getUser()),
      escape: false,
      icon: 'pi pi-user',
      items: [
        {
          label: 'Mi Cuenta',
          icon: 'pi pi-sign-in',
          routerLink: '/dashboard'
        },
        {
          label: 'Cerrar Sesión',
          icon: 'pi pi-user-plus',
          routerLink: '/auth',
          command: (onClick) => {
            this.authService.logout();
  
          }
        }
      ]
    }
  
    
  
  
      ];

  

  getUser():string {
    return localStorage.getItem('username')!;   
  }


   
  }
  





