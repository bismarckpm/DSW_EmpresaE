import { Component, OnInit } from '@angular/core';
import {UsuarioService} from '../../../services/usuario.service';
import {Usuario} from '../../../models/usuario';

@Component({
  selector: 'app-registrarse',
  templateUrl: './registrarse.component.html',
  styleUrls: ['./registrarse.component.css']
})
export class RegistrarseComponent implements OnInit {

  usuario: Usuario = {
    _id: 0,
    nombre: '',
    nombre2: '',
    apellido: '',
    apellido2: '',
    estado: '',
    fechaNacimiento: '',
  };

  constructor(private usuarioService: UsuarioService) {
  }

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  guardarUsuario() {
   /* this.usuarioService.registarUsuario(this.usuario)
      .subscribe(
        res => {
          console.log(res);
        },
        err => console.error(err)
      );*/
  }
}
