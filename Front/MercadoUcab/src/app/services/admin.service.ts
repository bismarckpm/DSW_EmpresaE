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
    'Encuestas',
    'Preguntas',
    'Categorias',
    'Subcategorias',
    'Marcas',
    'Presentaciones',
    'Tipos'
  ];

  constructor() { console.log('servicio ADMIN'); }

  getSecciones(): string[] {
    return this.secciones;
  }

  getSeccion(i): string {
    return this.secciones[i];
  }
}
