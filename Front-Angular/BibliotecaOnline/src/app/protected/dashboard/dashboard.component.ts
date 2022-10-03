import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/services/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
rol: string = localStorage.getItem('role') || '';


lendings : any[]= [];
  ProtectedService: any;
  private baseUrl: string = environment.baseUrl;
  get usuario() {

    return this.authService.user; 
  }
  constructor( private router: Router,
               private authService: AuthService,
               private http: HttpClient) { }
                           //No me toma la interface asi que por ahora que devuelva any, a la bosta
               traerLendings(): Observable<any> {

                const url = `${this.baseUrl}/lendings/`;
                const headers = new HttpHeaders()
                .set('Authorization',localStorage.getItem('token') || ''); // o String vacio. 
                return this.http.get<any>(url, { headers }) 
            
              }

  ngOnInit(): void {
    this.traerlosLendings();
   
   
    
    console.log('los lendings')
  
  }
               

traerlosLendings() {

this.traerLendings().subscribe (resp => {
  console.log(resp.content);
  this.lendings = resp.content;


})
}
}