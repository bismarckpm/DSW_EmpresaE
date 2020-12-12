import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from 'src/app/models/cliente';
import { Estudio } from 'src/app/models/estudio';

import { EstudioService } from 'src/app/services/estudio.service';

@Component({
  selector: 'app-lista-estudio-cliente',
  templateUrl: './lista-estudio.component.html',
  styleUrls: ['./lista-estudio.component.css']
})
export class ListaEstudioClienteComponent implements OnInit {

 //Declaracion de variables
 estudios: Estudio[]=[];
 _id = this.actRoute.snapshot.params['_id'];






  constructor(
    public estudioService: EstudioService,
    public actRoute: ActivatedRoute,
    public router: Router,

    ) { }

  ngOnInit(): void {
   this.loadEstudios();
  }

  loadEstudios(): void {
  let id = JSON.parse(localStorage.getItem("usuarioID"));
    this.estudioService.getEstudioCliente(id).subscribe(data => {
      this.estudios = data;
    });
  }

  deleteEstudio(id) {
    this.estudioService.deleteEstudio(id).subscribe(data => {
      this.loadEstudios();
    });
  }



}
