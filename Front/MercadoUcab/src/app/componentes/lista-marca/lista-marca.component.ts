import { Component, OnInit } from '@angular/core';
import {Marca} from '../../models/marca';
import {MarcaService} from '../../services/marca.service';

@Component({
  selector: 'app-lista-marca',
  templateUrl: './lista-marca.component.html',
  styleUrls: ['./lista-marca.component.css']
})
export class ListaMarcaComponent implements OnInit {

  marcas: Marca[] = [];


  // tslint:disable-next-line:variable-name
  constructor(private _servicio: MarcaService) {
    this.marcas = this._servicio.getMarcas();

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
