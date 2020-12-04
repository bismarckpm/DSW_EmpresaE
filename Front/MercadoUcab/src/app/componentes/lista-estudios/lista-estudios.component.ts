import { Component, OnInit } from '@angular/core';
import { Estudio } from 'src/app/models/estudio';
import { EstudioService } from 'src/app/services/estudio.service';

@Component({
  selector: 'app-lista-estudios',
  templateUrl: './lista-estudios.component.html',
  styleUrls: ['./lista-estudios.component.css']
})
export class ListaEstudiosComponent implements OnInit {

  estudios: any[] = [];

  constructor(private servicio: EstudioService) {
    //this.estudios = this.servicio.getEstudios();
   }

  ngOnInit(): void {
  }

}
