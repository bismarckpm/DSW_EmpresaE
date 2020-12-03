import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form-tipo',
  templateUrl: './form-tipo.component.html',
  styleUrls: ['./form-tipo.component.css']
})
export class FormTipoComponent implements OnInit {

  item: any = {
    descripcion: '',
    nombre: '',
    estado: ''
  };

  constructor() { }

  ngOnInit(): void {
  }

  agregarTipo(){
    console.log('agregó presentacion');
  }
}
