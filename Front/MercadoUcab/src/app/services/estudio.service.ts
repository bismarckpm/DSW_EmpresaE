import { Injectable } from '@angular/core';
import { Estudio } from '../models/estudio';

@Injectable({
  providedIn: 'root'
})
export class EstudioService {

  estudios: Estudio[] = [
    {
      id: 1,
      estado: 'A',
      nombre: 'Estudio 1',
      edadMin: 20,
      edadMax: 30,
      fechaI: '10/10/2010',
      fechaF: '20/12/2010'
    },
    {
      id: 2,
      estado: 'A',
      nombre: 'Estudio 2',
      edadMin: 20,
      edadMax: 30,
      fechaI: '10/10/2010',
      fechaF: '20/12/2010'
    },
  ];

  constructor() { }

  public getEstudios(): Estudio[] {
    return this.estudios;
  }


}
