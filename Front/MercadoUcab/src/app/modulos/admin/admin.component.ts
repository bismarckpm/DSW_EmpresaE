import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  seccion: any;
  item: any = {
    nombre: ''
  };


  constructor(
    private ruta: ActivatedRoute,
    private servicio: AdminService,
    private rutaActual: Router
  ) {
    this.ruta.params.subscribe(params => {
      console.log(params);
      this.seccion = this.servicio.getSeccion(params.id);
    });
    console.log(this.rutaActual.url);
  }

  ngOnInit(): void {
  }

}
