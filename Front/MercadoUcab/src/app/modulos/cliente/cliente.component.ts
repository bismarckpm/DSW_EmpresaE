import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClienteService } from 'src/app/services/cliente.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {
  seccion: any;
  item: any = {
    nombre: ''
  };

  accion = 1;

  
  constructor(
    private ruta: ActivatedRoute,
    private servicio: ClienteService,
    private rutaActtual: Router
  ) { this.ruta.params.subscribe(params => {
    console.log(params);
    this.seccion = this.servicio.getSeccion(params.id);
  });
  console.log(this.rutaActtual.url);
}

  ngOnInit(): void {
  }

}
