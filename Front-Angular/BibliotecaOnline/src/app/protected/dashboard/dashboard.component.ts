import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../auth/services/auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, Observable, of, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LibrosService } from '../../libros/service/libro.service';
import { Libro } from 'src/app/libros/interfaces/libro.interface';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


  rol: string = localStorage.getItem('role') || ''

  lendings: any[] = [];
  lendingsPendientes: any[] = [];
  baseUrl: string = environment.baseUrl;
  exitoso: boolean = false;
  problema: boolean = false;

  @Output() onEnter: EventEmitter<string> = new EventEmitter(); // Evento de salida para que dispare la busqueda hacia el componente buscador
  termino: string = '';
  // FORM DE CREACION LIBROS 

  setLibro: FormGroup = this.fb.group({

    title: ['', [Validators.required]],
    edit: ['', [Validators.required]],
    category: ["", [Validators.required]],
    ejemplares: ["", [Validators.required]],
    stock: ["", [Validators.required]],
    pages: ["", [Validators.required]],
    description: ["", [Validators.required]],
    lang: ["", [Validators.required]],
    date: ["", [Validators.required]],
    author: ["", [Validators.required]],
    img: ["", [Validators.required]]

  })





  constructor(private http: HttpClient,
    private fb: FormBuilder,
    private messageService: MessageService,
    private confirmationService: ConfirmationService
  ) { }


  postBook(title: string, edit: string, category: string, ejemplares: number, stock: number, pages: number, description: string, lang: string, date: string, author: string, img: string) {


    const url = `${this.baseUrl}/books/`;
    const body = { title, edit, category, ejemplares, stock, pages, description, lang, date, author, img };
    const headers = new HttpHeaders()
      .set('Authorization', localStorage.getItem('token') || ''); // o String vacio. 
    return this.http.post<any>(url, body, { headers })
      .pipe(
        tap(resp => {
          if (resp.ok === true) {
            console.log('Registro exitoso') //Confia en mi typescript ermozo
          }
        }),
        map(resp => of(resp.ok)),
        catchError(err => of(false))

      )
  }

  traerLendings(): Observable<any> {

    const url = `${this.baseUrl}/lendings/`;
    const headers = new HttpHeaders()
      .set('Authorization', localStorage.getItem('token') || ''); // o String vacio. 
    return this.http.get<any>(url, { headers })

  }

  traerLendingsAdmin(dni: string): Observable<any> {

    const url = `${this.baseUrl}/lendings/?dni=${dni}`;
    const headers = new HttpHeaders()

      .set('Authorization', localStorage.getItem('token') || ''); // o String vacio. 
    return this.http.get<any>(url, { headers })

  }

  traerLendingsPendientes(): Observable<any> {
    const pending: boolean = true
    const url = `${this.baseUrl}/lendings/?pending=${pending}`;
    const headers = new HttpHeaders()
      .set('Authorization', localStorage.getItem('token') || ''); // o String vacio. 
    return this.http.get<any>(url, { headers })

  }

  getLibroId(id: string): Observable<Libro> {

    const url = `http://localhost:8090/books/?id=${id}`;

    return this.http.get<Libro>(url); //retorna solo un libro no un arreglo

  }
  retornarLending(id: number):Observable<any> {

    const url = `${this.baseUrl}/lendings/${id}`;
    const headers = new HttpHeaders()
      .set('Authorization', localStorage.getItem('token') || ''); // o String vacio. 
   
    return this.http.post<any>(url, { headers })
  }


  ngOnInit(): void {

    if (this.rol === 'USUARIO') {
      this.traerlosLendings();
      this.traerlosLendingsPendientes()
    }
  }



  //Metodos Admin 

  enviarLibro() {
    const { title, edit, category, ejemplares, stock, pages, description, lang, date, author, img } = this.setLibro.value;


    this.postBook(title, edit, category, ejemplares, stock, pages, description, lang, date, author, img)
      .subscribe(ok => {
        if (ok) {
          console.log('Libro creado')
          this.exitoso = true;
        } else {
          this.problema = true;
          console.log('Error al crear libro')
        }
      })


    console.log(this.setLibro.value)



  }



  //Metodos USUARIO

  traerlosLendings() {

    this.traerLendings().subscribe(resp => {
      console.log(resp.content);
      this.lendings = resp.content;



    })
  }


  traerlosLendingsAdmin(dni: string) {
    this.traerLendingsAdmin(dni).subscribe(resp => {
      console.log(resp.content);
      this.lendings = resp.content;
    }
    )
  }


  traerlosLendingsPendientes() {

    this.traerLendingsPendientes().subscribe(resp => {
      console.log(resp.content);
      this.lendingsPendientes = resp.content;


    })
  }



  buscar(): void {
    this.onEnter.emit(this.termino); // Emitimos el evento de salida
    console.log(this.termino)
    this.traerLendingsAdmin(this.termino).subscribe(resp => 
      this.lendings = resp.content
      )
  }

  retornar(id: number) {
    console.log("Retornando....")
    this.retornarLending(id).subscribe(resp => {
      console.log(resp)
      this.traerlosLendingsPendientes();
    }
  )}

  
  }


