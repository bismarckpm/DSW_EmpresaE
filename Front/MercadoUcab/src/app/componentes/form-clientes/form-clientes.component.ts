import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-form-clientes',
  templateUrl: './form-clientes.component.html',
  styleUrls: ['./form-clientes.component.css']
})
export class FormClientesComponent implements OnInit {

  item: any = {
    nombre: ''
  };

  constructor() { }

  ngOnInit(): void {
  }


  agregarCliente() {
    console.log(this.item);
  }

 /* agregarCliente(): void {
    console.log('agrego: ' + this.item.nombre);
    this.item.nombre = '';

  }*/

}
