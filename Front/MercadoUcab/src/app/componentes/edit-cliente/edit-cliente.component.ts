import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-cliente',
  templateUrl: './edit-cliente.component.html',
  styleUrls: ['./edit-cliente.component.css']
})
export class EditClienteComponent implements OnInit {

  item: any = {
    nombre: ''
  };

  constructor() { }

  ngOnInit(): void {
  }

  actualizarCliente(): void {
      console.log('actualizo: ' + this.item.nombre);
      this.item.nombre = '';
  }

}
