import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/models/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {

  usuarios: Usuario[] = [];

  // tslint:disable-next-line: variable-name
  constructor(private _servicio: UsuarioService) {
    //this.usuarios = this._servicio.getUsuarios();
  }

  ngOnInit(): void {
  }

  eliminar(){
    console.log('eliminó elemento');
  }

  actualizar(){
    console.log('actualizó elemento');
  }

}
