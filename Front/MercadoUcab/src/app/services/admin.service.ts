import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  secciones: string[] = ['clientes', 'usuarios', 'analistas', 'estudios', 'preguntas', 'categoria', 'subcategoria', 'marca'];

  constructor() { console.log('servicio ADMIN'); }

  getSecciones() {
    return this.secciones;
  }

  getSeccion(i) {
    return this.secciones[i];
  }
}
