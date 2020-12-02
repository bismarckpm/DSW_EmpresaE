import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Encuestado } from '../models/encuestado';


@Injectable({
  providedIn: 'root'
})
export class EncuestadoService {

  API_URI = 'http://';
  encuestados: Encuestado[] = [
    {
      id: 0,
      nombre: 'prueba0',
      nombre2: '',
      apellido: '',
      apellido2: '',
      email: '',
      password: '',
      rol: '',
      estado: 'a',
    },
    {
      id: 1,
      nombre: 'prueba1',
      nombre2: '',
      apellido: '',
      apellido2: '',
      email: '',
      password: '',
      rol: '',
    },
  ];

  constructor( private http: HttpClient) { }

  getEncuestados(): Encuestado[] {
    return this.encuestados;
  }

  getEncuestado(){
    return this.http.get(`${this.API_URI}/encuestado`);
  }

  registarEncuestado(encuestado: Encuestado){
    return this.http.post(`${this.API_URI}/encuestado`, encuestado);
  }

  updateEncuestado(id , updateEncuestado){
    return this.http.post(`${this.API_URI}/encuestado/${id}`, updateEncuestado);
  }
}
