import { Component, OnInit } from '@angular/core';
import { AnalistaService } from 'src/app/services/analista.service';
import { ClienteService } from 'src/app/services/cliente.service';
import { EncuestadoService } from 'src/app/services/encuestado.service';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  secciones: string[] = [];
  rol = '';

  constructor(
    private _adminService: AdminService,
    private encuestadoService: EncuestadoService,
    private analistaService: AnalistaService,
    private clienteService: ClienteService ) {

    if ((JSON.parse(localStorage.getItem('rol') )) == 'Administrador'){
      this.secciones = this._adminService.getSecciones();
      this.rol = 'admin';
    }

    if ((JSON.parse(localStorage.getItem('rol') )) == 'Cliente'){
      this.secciones = this.clienteService.getSecciones();
      this.rol = 'cliente';
    }

    if ((JSON.parse(localStorage.getItem('rol') )) == 'Encuestado'){
      this.secciones = this.encuestadoService.getSecciones();
      this.rol = 'encuestado';
    }

    if ((JSON.parse(localStorage.getItem('rol') )) == 'Analista'){
      this.secciones = this.analistaService.getSecciones();
      this.rol = 'analista';
    }


  }
}
