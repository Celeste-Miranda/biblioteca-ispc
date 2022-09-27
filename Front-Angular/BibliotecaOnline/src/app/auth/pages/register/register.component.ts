import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
    userCredential: this.fb.group({
    username: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
  })
  });

  constructor( private fb: FormBuilder) { }

register(){

  console.log(this.miFormulario.value)
  console.log(this.miFormulario.valid) 

}
}
