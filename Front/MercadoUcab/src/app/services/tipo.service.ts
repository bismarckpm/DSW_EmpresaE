import { Injectable } from '@angular/core';
import {Tipo} from '../models/tipo';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TipoService {

  API_URI = 'https//';
  tipos: Tipo[] = [
    {
      id: 0,
      nombre: 'tipo2',
      descripcion: 'tipo producto x',
      estado: 'a',
    },
    {
      id: 1,
      nombre: 'tipo1',
      descripcion: 'tipo producto y',
      estado: 'i',
    },
  ];

  constructor(private http: HttpClient) { }

    getTipos(): Tipo[] {
      return this.tipos;
    }


    getTipo(){
      return this.http.get(`${this.API_URI}/tipo`);
    }

    registarTipo(tipo: Tipo){
      return this.http.post(`${this.API_URI}/tipo`, tipo);
    }

    updateTipo(id , updateTipo){
      return this.http.post(`${this.API_URI}/presentacion/${id}`, updateTipo);
    }
}
