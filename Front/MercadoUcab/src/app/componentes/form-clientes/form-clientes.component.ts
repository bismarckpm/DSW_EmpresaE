import { Component, OnInit } from '@angular/core';

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

  agregarNombre() {
    console.log(this.item);
  }

}
