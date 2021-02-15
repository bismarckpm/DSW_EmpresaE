import {Component, Input, OnInit} from '@angular/core';
import {Tipo} from '../../models/tipo';
import {TipoService} from '../../services/tipo.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastService} from "../../services/toast.service";

@Component({
  selector: 'app-lista-tipo',
  templateUrl: './lista-tipo.component.html',
  styleUrls: ['./lista-tipo.component.css']
})
export class ListaTipoComponent implements OnInit {

  aux: any;
  Tipo: Tipo[] = [];
  _id = this.actRoute.snapshot.params._id;
  @Input() tipoData = {_id: 0, nombre: '', estado: '', descripcion: ''};

  formTipo: FormGroup;
  patronNombreTipo: any = /^[A-Za-z0-9\s]+$/;

  // tslint:disable-next-line:variable-name
  constructor(
    private formBuilder: FormBuilder,
    public actRoute: ActivatedRoute,
    public router: Router,
    private toast: ToastService,
    public tipoService: TipoService) {
    this.createForm();  }

  ngOnInit(): void {
    this.loadTipo();
  }

  loadTipo(): void {
    this.tipoService.getTipos().subscribe(data => {
      this.Tipo = data;
    });
  }

  deleteTipo(id) {
    this.tipoService.deleteTipo(id).subscribe(data => {
      this.loadTipo();
    });
  }

  updateTipo(){
    this.tipoService.updateTipo(this.tipoData._id, this.tipoData).subscribe(data => {
    });
  }

  editar(tipo){
    this.tipoData = tipo;
  }

  HandleErrorGet(){
    if (this.aux.estado == 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
      this.Tipo = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }

  HandleErrorPostPut(){
    if (this.aux.estado == 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }

  /// Validacion de Datos
  get nombreTipo(){
    return this.formTipo.get('nombreTipo');
  }

  get estadoTipo(){
    return this.formTipo.get('estadoTipo');
  }

  get descripcionTipo(){
    return this.formTipo.get('descripcionTipo');
  }

  createForm(){
    this.formTipo = this.formBuilder.group({
      nombreTipo: ['', [Validators.pattern(this.patronNombreTipo), Validators.required]],
      estadoTipo: ['', Validators.required],
      descripcionTipo: ['', [Validators.required, Validators.pattern(this.patronNombreTipo)]],
    });
  }
}
