import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
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
        this._user = {
          username: resp.username!
        }
      }
    }),
    map(resp => of(resp.bearer)),
    catchError(err => of(false))
     
   )
  }



}
