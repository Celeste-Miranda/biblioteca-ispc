import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';

import { AuthResponse, User } from '../interfaces/auth.interfaces';
import { catchError, map, of, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl: string = environment.baseUrl;
  private _user!: User; //Confía en mi, Typescript. 

  get user() {
    return {...this._user};
  }

  constructor( private http: HttpClient) { }



  login ( username: string, password: string ) {

    const url = `${ this.baseUrl}/login`;
    const body = { username, password}

   return this.http.post<AuthResponse>(url, body)
   .pipe(
    tap( resp => {
      if (resp.bearer === "Bearer") {
        localStorage.setItem('token' , ('Bearer ' + resp.token!)) //Confia en mi typescript ermozo
        this._user = {
          username: resp.username!
        }
      }
    }),
    map(resp => of(resp.bearer)),
    catchError(err => of(false))
     
   )
  }

  //PARA HACER/TESTEAR
  validarToken() {

    const url = `${this.baseUrl}/valid`;
    const headers = new HttpHeaders()
    .set('Authorization',localStorage.getItem('token') || ''); // o String vacio. 
    return this.http.get(url, { headers });

//SETEAR EN EL LOCALSTORAGE el token como  "Bearer (espacio) valordelToken" para que tome bien 
//SHA ESTÁ ESHHOOO
  }


}
