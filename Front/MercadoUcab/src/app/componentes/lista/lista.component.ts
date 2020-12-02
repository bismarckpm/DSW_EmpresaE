import { Component, OnInit } from '@angular/core';
import { Encuestado } from 'src/app/models/encuestado';
import { EncuestadoService } from 'src/app/services/encuestado.service';

@Component({
  selector: 'app-lista',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.css']
})
export class ListaComponent implements OnInit {

  encuestados: Encuestado[] = [];

  // tslint:disable-next-line: variable-name
  constructor(private _servicio: EncuestadoService) {
    this.encuestados = this._servicio.getEncuestados();
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
