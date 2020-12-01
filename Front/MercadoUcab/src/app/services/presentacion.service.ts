import { Injectable } from '@angular/core';
import {Presentacion} from '../models/presentacion';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PresentacionService {

  API_URI = 'http://';
  presentaciones: Presentacion[] = [
    {
      id: 0,
      descripcion: 'presentacion de producto x',
      estado: 'a',
    },
    {
      id: 1,
      descripcion: 'presentacion de producto y',
      estado: 'i',
    },
  ];

  constructor(private http: HttpClient) { }

  getPresentaciones(): Presentacion[] {
    return this.presentaciones;
  }


  getPresentacion() {
    return this.http.get(`${this.API_URI}/presentacion`);
  }

  registarPresentacion(presentacion: Presentacion){
    return this.http.post(`${this.API_URI}/presentacion`, presentacion);
  }

  updatePresentacion(id ,updatePresentacion){
    return this.http.post(`${this.API_URI}/presentacion/${id}`,updatePresentacion);
  }
}
