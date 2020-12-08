import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TipoService} from '../../services/tipo.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-form-tipo',
  templateUrl: './form-tipo.component.html',
  styleUrls: ['./form-tipo.component.css']
})
export class FormTipoComponent implements OnInit {

  @Input() tipo = {id: 0, nombre: '', estado: '', descripcion: ''};
  formTipo: FormGroup;
  patronNombreTipo: any = /^[A-Za-z0-9\s]+$/;

  constructor(
    private formBuilder: FormBuilder,
    public tipoService: TipoService,
    public router: Router) {
    this.createForm();
  }

  ngOnInit(): void {
  }

  addTipo(tipo){
    if (this.formTipo.valid) {
      this.tipoService.createTipo(this.tipo).subscribe((data: {}) => {
      });
    }
    else{
      alert(' LLenar todos los campos por favor');
    }
  }

  get nombreTipo(){
    return this.formTipo.get('nombreTipo');
  }

  get descripcionTipo(){
    return this.formTipo.get('descripcionTipo');
  }

  get estadoTipo(){
    return this.formTipo.get('estadoTipo');
  }

  createForm(){
    this.formTipo = this.formBuilder.group({
      nombreTipo: ['', [Validators.pattern(this.patronNombreTipo), Validators.required]],
      descripcionTipo: ['', [Validators.pattern(this.patronNombreTipo), Validators.required]],
      estadoTipo: ['', Validators.required],
    });
  }

  agregarTipo(){
    console.log('agregó presentacion');
  }
}
