import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AnalistaService } from 'src/app/services/analista.service';

@Component({
  selector: 'app-analista',
  templateUrl: './analista.component.html',
  styleUrls: ['./analista.component.css']
})
export class AnalistaComponent implements OnInit {
  seccion: any;
  item: any = {
    nombre: ''
  };

  accion = 1;

  constructor(
    private ruta: ActivatedRoute,
    private servicio: AnalistaService,
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
