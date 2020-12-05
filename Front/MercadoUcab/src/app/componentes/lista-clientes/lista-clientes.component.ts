import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/models/cliente';
import { ClienteService } from 'src/app/services/cliente.service';

@Component({
  selector: 'app-lista-clientes',
  templateUrl: './lista-clientes.component.html',
  styleUrls: ['./lista-clientes.component.css']
})
export class ListaClientesComponent implements OnInit {

  clientes: Cliente[] = [];

  // tslint:disable-next-line:variable-name
  constructor(private _servicio: ClienteService) {
    this.clientes = this._servicio.getClientes();
   }

  ngOnInit(): void {
  }


  eliminar(){
    console.log('eliminó elemento');
  }

  actualizar(){
    console.log('actualizó elemento');

  public eliminarCliente(clienteEliminar: Cliente): void{
    console.log('elimino ' + clienteEliminar.id);

  }

}
