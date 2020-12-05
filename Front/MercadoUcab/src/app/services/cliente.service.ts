import { Injectable } from '@angular/core';
import { Cliente } from '../models/cliente';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  API_URI = 'http://';
  clientes: Cliente[] = [
    {
      id: 1,
      estado: 'A',
      razonSocial: 'J',
      rif: 10012010
    },
    {
      id: 2,
      estado: 'A',
      razonSocial: 'J',
      rif: 202002
    },
  ];

  constructor(private http: HttpClient) { }

   getClientes(): Cliente[] {
    return this.clientes;
  }
  getCliente() {
    return this.http.get(`${this.API_URI}/cliente`);
  }

  registarCliente(marca: Cliente){
    return this.http.post(`${this.API_URI}/cliente`, marca);
  }

  updateCliente(id , updateCliente){
    return this.http.post(`${this.API_URI}/cliente/${id}`, updateCliente);
  }
}
