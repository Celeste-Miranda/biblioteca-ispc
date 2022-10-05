import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ok } from 'assert';
import { AuthService } from '../../services/auth.service';
import { catchError, tap } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  miFormulario: FormGroup = this.fb.group({

    name: ['',[Validators.required]],
    lastname: ['',[Validators.required]],
    address: ["",[Validators.required]],
    tel: ["",[Validators.required]],
    dni: ["",[Validators.required]],
    userCredential: this.fb.group({
    username: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
  })
  });

  constructor( private fb: FormBuilder,
               private router: Router,
               private authService: AuthService) { }

register(){
  const {name, lastname, address, tel, dni, userCredential} = this.miFormulario.value;


   
  this.authService.register(name, lastname, address, tel, dni, userCredential)
  



}
}
