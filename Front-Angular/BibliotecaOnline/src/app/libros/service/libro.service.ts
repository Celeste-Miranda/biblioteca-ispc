import { HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import { Injectable } from "@angular/core";
import { catchError, map, Observable, of, tap } from 'rxjs';
import { AuthResponse } from 'src/app/auth/interfaces/auth.interfaces';
import { Libro } from '../interfaces/libro.interface';
import { ResponseBack } from '../../auth/interfaces/responseBack.interface';


@Injectable({
    providedIn: "root"
})  

export class LibrosService {

    private apiUrl: string = "http://localhost:8090"

    constructor(private http: HttpClient) { }

    buscarLibros(termino: string): Observable<Libro[]> {
       
        const url = `${this.apiUrl}/books?title=${termino}`;

        return this.http.get<Libro[]>(url);
    }

    getLibroId( id: string ): Observable<Libro>{
      
        const url = `${ this.apiUrl }/books/${id}`;
        
        return this.http.get<Libro>(url); //retorna solo un pais no un arreglo
    
      }

      register(libroId: string){

        const url = `${ this.apiUrl}/lendings`;
        const body = { libroId };
    
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

      userLending(bookId: number): Observable<any[]> {


        const url = `${ this.apiUrl}/lendings`;
        const body = { bookId };
        const headers = new HttpHeaders()
        .set('Authorization',localStorage.getItem('token') || ''); // o String vacio. 
    
       return this.http.post<any[]>(url, body, {headers})

}
}