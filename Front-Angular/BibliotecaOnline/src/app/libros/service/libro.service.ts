import { HttpClient, HttpParams} from '@angular/common/http';
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { Libro } from '../interfaces/libro.interface';


@Injectable({
    providedIn: "root"
})  

export class LibrosService {

    private apiUrl: string = "http://localhost:8090"

    constructor(private http: HttpClient) { }

    buscarLibros(termino: string): Observable<Libro[]> {
       
        const url = `${this.apiUrl}/book?title=${termino}`;

        return this.http.get<Libro[]>(url);
    }

    getLibroId( id: string ): Observable<Libro>{
      
        const url = `${ this.apiUrl }/book/${id}`;
        
        return this.http.get<Libro>(url); //retorna solo un pais no un arreglo
    
      }

}