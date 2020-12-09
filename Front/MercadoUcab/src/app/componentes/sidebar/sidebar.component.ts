import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {

  secciones: string[] = [];

  // tslint:disable-next-line: variable-name
  constructor(private _adminService: AdminService) {
    this.secciones = this._adminService.getSecciones();
  }

}
