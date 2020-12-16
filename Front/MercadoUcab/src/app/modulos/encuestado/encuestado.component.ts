import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EncuestadoService } from 'src/app/services/encuestado.service';

@Component({
  selector: 'app-encuestado',
  templateUrl: './encuestado.component.html',
  styleUrls: ['./encuestado.component.css']
})
export class EncuestadoComponent implements OnInit {

  seccion: any;
  item: any = {
    nombre: ''
  };

  accion = 1;

  constructor(
    private ruta: ActivatedRoute,
    private servicio: EncuestadoService,
    private rutaActtual: Router
  ) {
    this.ruta.params.subscribe(params => {
      console.log(params);
      this.seccion = this.servicio.getSeccion(params.id);
    });
    console.log(this.rutaActtual.url);
  }

  ngOnInit(): void {
  }

}
