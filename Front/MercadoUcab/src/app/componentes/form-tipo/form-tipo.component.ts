import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-form-tipo',
  templateUrl: './form-tipo.component.html',
  styleUrls: ['./form-tipo.component.css']
})
export class FormTipoComponent implements OnInit {

  formTipo: FormGroup;
  patronNombreTipo: any = /^[A-Za-z0-9\s]+$/;

  get nombreTipo(){
    return this.formTipo.get('nombreTipo');
  }

  get descripcionTipo(){
    return this.formTipo.get('descripcionTipo');
  }

  get estadoTipo(){
    return this.formTipo.get('estadoTipo');
  }

  constructor(private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm(){
    this.formTipo = this.formBuilder.group({
      nombreTipo: ['', [Validators.pattern(this.patronNombreTipo), Validators.required]],
      descripcionTipo: ['', Validators.required],
      estadoTipo: ['', Validators.required],
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.formTipo.valid) {
      console.log(this.formTipo.value);
    }
    else{
      alert('FILL ALL FIELDS');
    }
  }

  agregarTipo(){
    console.log('agregó presentacion');
  }
}
