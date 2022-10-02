import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';

import { AuthResponse, User } from '../interfaces/auth.interfaces';
import { catchError, map, of, tap, Observable } from 'rxjs';

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

  register(name: string, lastname: string, address: string, tel: number, dni: number, userCredential:[ username: string, password: string ] ){

   
    const url = `${ this.baseUrl}/register`;
    const body = { name, lastname, address, tel, dni, userCredential };

   return this.http.post<AuthResponse>(url, body)
   .pipe(
    tap( resp => {
      if (resp.ok === true) {
        console.log('Registro exitoso') //Confia en mi typescript ermozo
      }
    }),
    map(resp => of(resp.ok)),
    catchError(err => of(false))
     
   )
  }




  login ( username: string, password: string ) {

    const url = `${ this.baseUrl}/login`;
    const body = { username, password}

   return this.http.post<AuthResponse>(url, body)
   .pipe(
    tap( resp => {
      if (resp.bearer === "Bearer") {
        localStorage.setItem('token' , ('Bearer ' + resp.token!)) //Se antepone el bearer para que header funcione bien
        localStorage.setItem('username', resp.username!);
        localStorage.setItem('role', resp.role!);
      }
    }),
    map(resp => of(resp.bearer)),
    catchError(err => of(false))
     
   )
  }

  logout() {
    localStorage.clear();
  }

  //PARA HACER/TESTEAR
  validarToken(): Observable<boolean> {

    const url = `${this.baseUrl}/valid`;
    const headers = new HttpHeaders()
    .set('Authorization',localStorage.getItem('token') || ''); // o String vacio. 
    return this.http.get<AuthResponse>(url, { headers })
    .pipe(
      map( resp => {
        this._user = {
          username: resp.username!
        }
        return true
      }), catchError (err => of(false))
    );

//SETEAR EN EL LOCALSTORAGE el token como  "Bearer (espacio) valordelToken" para que tome bien 
//SHA ESTÁ ESHHOOO
  }


}
