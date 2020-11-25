import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/models/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';


@Component({
  selector: 'app-registrarse',
  templateUrl: './registrarse.component.html',
  styleUrls: ['./registrarse.component.css']
})
export class RegistrarseComponent implements OnInit {

  usuario: Usuario ={
    
    nombre:'',
    nombre2:'',
    apellido:'',
    apellido2:'',
    email:'',
    password:'',
    rol:'',
  };

  constructor(private usuarioService:UsuarioService) {

    
   }

  ngOnInit(): void {
  }
  guardarUsuario(){
    this.usuarioService.registarUsuario(this.usuario)
    .subscribe(
      res=>{
        console.log(res)
      },
      err=> console.error(err)
    )
  }
}
