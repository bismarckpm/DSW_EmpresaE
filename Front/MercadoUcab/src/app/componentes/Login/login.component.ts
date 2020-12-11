import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { Usuario } from 'src/app/models/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  

  @Input() usuario:{ username:'', clave:''}
  formLogin: FormGroup;

  constructor(
    public usuarioService:UsuarioService,
    private formBuilder: FormBuilder
  ) { this.createForm() }

  ngOnInit(): void {
  }
  


Loggearse(){
  if (this.formLogin.invalid) {
    return;
}

return this.usuarioService.Logear(this.usuario).subscribe( 
 data => {
        console.log(data);
    },
      error=> console.log(error)

);

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
      estadoMarca: ['', Validators.required],
    });
  }

}
