import { Component, OnInit } from '@angular/core';
import { Pregunta } from '../../models/pregunta';
import { PreguntaService } from '../../services/pregunta.service';

@Component({
  selector: 'app-lista-preguntas',
  templateUrl: './lista-preguntas.component.html',
  styleUrls: ['./lista-preguntas.component.css']
})
export class ListaPreguntasComponent implements OnInit {

  preguntas: Pregunta[] = [];

  // tslint:disable-next-line:variable-name
  constructor(private _servicio: PreguntaService) {
    this.preguntas = this._servicio.getPreguntas();
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
