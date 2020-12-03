import { Injectable } from '@angular/core';
import { Pregunta } from '../models/pregunta';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PreguntaService {

  API_URI = 'https//';
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

  constructor(private http: HttpClient) { }

  getPreguntas(): Pregunta[] {
    return this.preguntas;
  }

  getPregunta() {
    return this.http.get(`${this.API_URI}/pregunta`);
  }

  registarPregunta(pregunta: Pregunta){
    return this.http.post(`${this.API_URI}/pregunta`, pregunta);
  }

  updatePregunta(id , updatePregunta){
    return this.http.post(`${this.API_URI}/presentacion/${id}`, updatePregunta);
  }
}
