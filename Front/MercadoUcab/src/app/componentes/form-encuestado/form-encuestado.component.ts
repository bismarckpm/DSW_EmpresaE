import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form-encuestado',
  templateUrl: './form-encuestado.component.html',
  styleUrls: ['./form-encuestado.component.css']
})
export class FormEncuestadoComponent implements OnInit {

  item: any = {
    nombre: '',
    nombre2: '',
    apellido: '',
    apellido2: '',
    email: '',
    password: '',
    estado: ''
  };

  constructor() { }

  ngOnInit(): void {
  }

  agregarEncuestado(){
    console.log('agregó encuestado');
  }

}
