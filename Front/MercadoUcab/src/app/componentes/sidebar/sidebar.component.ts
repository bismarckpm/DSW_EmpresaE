import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  categorias: string[] = ['Clientes', 'Usuarios', 'Analistas', 'Estudios', 'Preguntas', 'Categoria', 'Subcategoria', 'Marca'];

  constructor() { }

}
