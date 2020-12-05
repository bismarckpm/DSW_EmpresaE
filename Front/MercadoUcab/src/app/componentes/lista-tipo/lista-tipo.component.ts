import { Component, OnInit } from '@angular/core';
import {Tipo} from '../../models/tipo';
import {TipoService} from '../../services/tipo.service';

@Component({
  selector: 'app-lista-tipo',
  templateUrl: './lista-tipo.component.html',
  styleUrls: ['./lista-tipo.component.css']
})
export class ListaTipoComponent implements OnInit {

  tipos: Tipo[] = [];

  // tslint:disable-next-line:variable-name
  constructor(private _servicio: TipoService) {
    //this.tipos = this._servicio.getTipos();
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
