import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {Encuestado} from '../../models/encuestado';
import {EncuestadoService} from '../../services/encuestado.service';
import {ToastService} from "../../services/toast.service";

@Component({
  selector: 'app-lista-encuestados',
  templateUrl: './lista-encuestados.component.html',
  styleUrls: ['./lista-encuestados.component.css']
})
export class ListaEncuestadosComponent implements OnInit {

  aux: any;
  Encuestado: Encuestado[] = [];
  _id = this.actRoute.snapshot.params['_id'];

  // Declaracion para los dropdown

  ///// Atributos para la busqueda de acuerdo a lo seleccionado

  // Declaracion para validar
  constructor(
    public actRoute: ActivatedRoute,
    public router: Router,
    private toast: ToastService,
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

  HandleErrorGet(){
    if(this.aux.estado == 'Exitoso'){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje);
      this.Encuestado = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje);
    }
  }

  HandleErrorPostPut(){
    if (this.aux.estado == 'Exitoso'){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje);
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje);
    }
  }

}

