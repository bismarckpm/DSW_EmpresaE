import { Component, Input, OnInit } from '@angular/core';
import { ClienteService } from 'src/app/services/cliente.service';

@Component({
  selector: 'app-form-clientes',
  templateUrl: './form-clientes.component.html',
  styleUrls: ['./form-clientes.component.css']
})
export class FormClientesComponent implements OnInit {

  item: any = {
    nombre: ''
  };

  constructor(
    publico clienteService:ClienteService
  ) { }

  ngOnInit(): void {
  }


  agregarCliente() {
    this.item.clienteService.
  }

 /* agregarCliente(): void {
    console.log('agrego: ' + this.item.nombre);
    this.item.nombre = '';

  }*/

}
