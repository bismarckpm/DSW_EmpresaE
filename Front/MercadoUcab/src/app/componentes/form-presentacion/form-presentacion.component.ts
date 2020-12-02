import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form-presentacion',
  templateUrl: './form-presentacion.component.html',
  styleUrls: ['./form-presentacion.component.css']
})
export class FormPresentacionComponent implements OnInit {

  item: any = {
    descripcion: '',
    estado: ''
  };

  constructor() { }

  ngOnInit(): void {
  }

  agregarPresentacion(){
    console.log('agregó presentacion');
  }
}
