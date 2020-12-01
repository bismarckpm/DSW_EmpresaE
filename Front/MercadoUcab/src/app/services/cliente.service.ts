import { Injectable } from '@angular/core';
import { Cliente } from '../models/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

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

  constructor() { }

  public getClientes(): Cliente[] {
    return this.clientes;
  }
}
