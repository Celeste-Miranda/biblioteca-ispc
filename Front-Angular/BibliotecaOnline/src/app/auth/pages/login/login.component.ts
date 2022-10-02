import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{


  miFormulario: FormGroup = this.fb.group({

    username: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]]

  });

  constructor( private fb: FormBuilder,
              private router: Router,
              private authService: AuthService) { }

  ngOnInit(): void {
    if( window.localStorage )
    {
      if( !localStorage.getItem('firstLoad') )
      {
        localStorage['firstLoad'] = true;
        window.location.reload();
      }
      else
        localStorage.removeItem('firstLoad');
    }
  }

login(){

  const { username, password} = this.miFormulario.value;

 this.authService.login(username,password)
 .subscribe(ok => {
  // console.log(ok);
  if (ok) {
    
    this.router.navigateByUrl('/dashboard')
    
  } else {
    //MOSTRAR MENSAJE ERROR
    console.log("ERROR PAPÁ")
  }
 });
  //this.router.navigateByUrl('/dashboard')

}
}
