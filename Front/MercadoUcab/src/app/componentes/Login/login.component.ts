import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/models/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  user :Usuario[]=[]
  @Input() usuario={username:'', clave:'' };
  formLogin: FormGroup;
 
  constructor(
    public usuarioService:UsuarioService,
    private formBuilder: FormBuilder,
    public router:Router
  ) { this.createForm() }

  ngOnInit(): void {
  }
  


Loggearse(){
   if (this.formLogin.valid) {
    return this.usuarioService.Logear(this.usuario)
    .subscribe( data => {
          
    })  
}

}







logout() {
  // remove user from local storage and set current user to null
  localStorage.removeItem('currentUser');
 // this.currentUserSubject.next(null);
}



  /// Validacion de Datos
  get userName(){return this.formLogin.get('userName');}

  get password(){return this.formLogin.get('password');}

  createForm(){
    this.formLogin = this.formBuilder.group({
      userName: ['',Validators.required] ,
      password: ['', Validators.required],
    });
  }

}
