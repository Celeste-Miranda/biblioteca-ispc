import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/services/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
rol: string = localStorage.getItem('role') || '';


nothing = [] = []; 

  get usuario() {

    return this.authService.user; 
  }
  constructor( private router: Router,
               private authService: AuthService) { }

  ngOnInit(): void {
   


    if( window.localStorage )
    {
      if( !localStorage.getItem('firstLoad') )
      {
        localStorage['firstLoad'] = true;
        window.location.reload();
      }  
      else
        localStorage.removeItem('firstLoad');
    }
  }
               

logout() {

this.authService.logout();
this.router.navigateByUrl('/auth')

}


}
