import { Component, OnInit } from '@angular/core';
import {Presentacion} from '../../models/presentacion';
import {PresentacionService} from '../../services/presentacion.service';

@Component({
  selector: 'app-lista-presentacion',
  templateUrl: './lista-presentacion.component.html',
  styleUrls: ['./lista-presentacion.component.css']
})
export class ListaPresentacionComponent implements OnInit {

  presentaciones: Presentacion[] = [];

  // tslint:disable-next-line:variable-name
  constructor(private _servicio: PresentacionService) {
    this.presentaciones = this._servicio.getPresentaciones();

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
