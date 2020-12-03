import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form-pregunta',
  templateUrl: './form-pregunta.component.html',
  styleUrls: ['./form-pregunta.component.css']
})
export class FormPreguntaComponent implements OnInit {

  item: any = {
    descripcion: '',
    estado: ''
  };

  constructor() { }

  ngOnInit(): void {
  }

  agregarPregunta(){
    console.log('agregó pregunta');
  }
}
