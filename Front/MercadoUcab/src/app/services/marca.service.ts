import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Marca } from '../models/marca';

@Injectable({
  providedIn: 'root'
})
export class MarcaService {

  API_URI = 'http://';
  marcas: Marca[] = [
    {
      id: 0,
      nombre: 'prueba0',
      estado: 'a',
    },
    {
      id: 1,
      nombre: 'prueba1',
      estado: 'i',
    },
  ];


  constructor( private http: HttpClient) { }

  getMarcas(): Marca[] {
    return this.marcas;
  }


  getMarca() {
    return this.http.get(`${this.API_URI}/marca`);
  }

  registarMarca(marca: Marca){
    return this.http.post(`${this.API_URI}/marca`, marca);
  }

  updateMarca(id ,updateMarca){
    return this.http.post(`${this.API_URI}/marca/${id}`,updateMarca);
  }

}
