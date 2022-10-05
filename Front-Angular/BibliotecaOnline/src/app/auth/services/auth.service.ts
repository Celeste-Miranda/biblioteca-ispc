import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../../../environments/environment';

import { AuthResponse, User } from '../interfaces/auth.interfaces';
import { catchError, map, of, tap, Observable } from 'rxjs';

type HttpOptions = {
  headers?: HttpHeaders | { [header: string]: string | string[]; };
  observe?: "body"; params?: HttpParams | { [param: string]: string | string[]; };
  reportProgress?: boolean; responseType?: "json" /* or "text" as "json" */;
  withCredentials?: boolean;
}

let get_http_options_text = (): HttpOptions => {
  return {
    headers: {'Content-Type': 'text/plain'},
    observe: "body",
    responseType: "text" as "json",  // @see https://github.com/angular/angular/issues/18586
    withCredentials: true
  }
}

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

  register(name: string, lastname: string, address: string, tel: number, dni: number, userCredential:[ username: string, password: string ] ) {

   
    const url = `${ this.baseUrl}/register`;
    const body = { name, lastname, address, tel, dni, userCredential };

   return this.http.post(url, body).subscribe(resp => {
      console.log(resp)
      alert('Registro exitoso')
      window.location.href = '/auth/login';

    },err => {
      alert(err.error.message)
      console.log(err)

    })
  


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


  }


}
