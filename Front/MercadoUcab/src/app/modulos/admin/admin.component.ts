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

  constructor(
    // private ruta: ActivatedRoute,
    // private servicio: AdminService,
    private rutaActtual: Router
  ) {
    // this.ruta.params.subscribe(params => {
    //   console.log(params);
    //   this.seccion = this.servicio.getSeccion(params.id);
    // });
    console.log(this.rutaActtual.url);
  }

  ngOnInit(): void {
  }

  agregar() {
    console.log('agregó elemento');
  }
}
