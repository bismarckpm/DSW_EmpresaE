import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-main-clienadmin',
  templateUrl: './main-clienadmin.component.html',
  styleUrls: ['./main-clienadmin.component.css']
})
export class MainClienadminComponent implements OnInit {

  seccion: string;

  constructor(
    private ruta: ActivatedRoute,
    private servicio: AdminService
  ) {
    this.ruta.params.subscribe(params => {
      console.log(params.id);
      this.seccion = this.servicio.getSeccion(params.i);
    });
  }

  ngOnInit(): void {
  }

  agregar() {
    console.log('Agregó cliente');
  }
}
