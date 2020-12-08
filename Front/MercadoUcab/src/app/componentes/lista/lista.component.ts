import { Component, OnInit } from '@angular/core';
import { EncuestadoService } from 'src/app/services/encuestado.service';
import {Encuestado} from '../../models/encuestado';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {

  encuestados: Encuestado[] = [];

  // tslint:disable-next-line: variable-name
  constructor(private _servicio: EncuestadoService) {
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
