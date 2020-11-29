import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  secciones: string[] = ['Clientes', 'Usuarios', 'Analistas', 'Estudios', 'Preguntas', 'Categorias', 'Subcategorias', 'Marcas'];

  constructor() { console.log('servicio ADMIN'); }

  getSecciones() {
    return this.secciones;
  }

  getSeccion(i) {
    return this.secciones[i];
  }
}
