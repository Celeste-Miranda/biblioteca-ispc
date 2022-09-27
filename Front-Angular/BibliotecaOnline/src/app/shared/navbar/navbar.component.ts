import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html'
})
export class NavbarComponent implements OnInit {

  items: MenuItem[] = []
  constructor() { }

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
