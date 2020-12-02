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

  constructor(private servicio: PreguntaService) {
    this.preguntas = this.servicio.getPreguntas();
  }

  ngOnInit(): void {
  }

}
