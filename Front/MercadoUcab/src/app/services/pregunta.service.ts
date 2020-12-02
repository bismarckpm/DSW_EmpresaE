import { Injectable } from '@angular/core';
import { Pregunta } from '../models/pregunta';

@Injectable({
  providedIn: 'root'
})
export class PreguntaService {

  preguntas: Pregunta[] = [
    {
      id: 20,
      estado: 'I',
      descripcion: 'Pregunta 1',
    },
    {
      id: 22,
      estado: 'A',
      descripcion: 'Pregunta 2',
    },
  ];

  constructor() { }

  public getPreguntas(): Pregunta[] {
    return this.preguntas;
  }
}
