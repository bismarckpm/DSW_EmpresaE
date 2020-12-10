import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from 'src/app/models/cliente';
import { Estudio } from 'src/app/models/estudio';

import { EstudioService } from 'src/app/services/estudio.service';

@Component({
  selector: 'app-lista-estudio',
  templateUrl: './lista-estudio.component.html',
  styleUrls: ['./lista-estudio.component.css']
})
export class ListaEstudioComponent implements OnInit {

 //Declaracion de variables
 estudios: Estudio[]=[];
 _id = this.actRoute.snapshot.params['_id'];

user: Cliente;


  constructor(
    public estudioService: EstudioService,
    public actRoute: ActivatedRoute,
    public router: Router,

    ) { }

  ngOnInit(): void {
   this.loadEstudios();
  }

  loadEstudios(): void {
    this.estudioService.getEstudioCliente(this.user).subscribe(data => {
      this.estudios = data;
    });
  }

  deleteEstudio(id) {
    this.estudioService.deleteEstudio(id).subscribe(data => {
      this.loadEstudios();
    });
  }



}
