import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  secciones: string[] = [
    'Inicio',
    'Clientes',
    'Usuarios',
    'Analistas',
    'Estudios',
    'Preguntas',
    'Categorias',
    'Subcategorias',
    'Marcas',
    'Presentaciones'
  ];

  constructor() { console.log('servicio ADMIN'); }

  getSecciones() {
    return this.secciones;
  }

  getSeccion(i) {
    return this.secciones[i];
  }
}
