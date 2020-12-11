import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {Encuestado} from '../../models/encuestado';
import {EncuestadoService} from '../../services/encuestado.service';

@Component({
  selector: 'app-lista-encuestados',
  templateUrl: './lista-encuestados.component.html',
  styleUrls: ['./lista-encuestados.component.css']
})
export class ListaEncuestadosComponent implements OnInit {


  Encuestado: Encuestado[] = [];
  _id = this.actRoute.snapshot.params['_id'];

  // Declaracion para los dropdown

  ///// Atributos para la busqueda de acuerdo a lo seleccionado

  // Declaracion para validar
  constructor(
    public actRoute: ActivatedRoute,
    public router: Router,
    public encuestadoService: EncuestadoService)
  {}

  ngOnInit(): void {
    this.loadEncuestados();
  }

  loadEncuestados(): void {
    this.encuestadoService.getEncuestados().subscribe(data => {
      this.Encuestado = data;
    });
  }

  deleteEncuestado(id) {
    this.encuestadoService.deleteEncuestado(id).subscribe(data => {
      this.loadEncuestados();
    });
  }
}

