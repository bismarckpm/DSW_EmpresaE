import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-form-encuestado',
  templateUrl: './form-encuestado.component.html',
  styleUrls: ['./form-encuestado.component.css']
})
export class FormEncuestadoComponent implements OnInit {

  formEncuestado: FormGroup;
  patronNombreEncuestado: any = /^[a-zA-Z]+$/;

  get primerNombreEncuestado(){
    return this.formEncuestado.get('primerNombreEncuestado');
  }

  get segundoNombreEncuestado(){
    return this.formEncuestado.get('segundoNombreEncuestado');
  }

  get primerApellidoEncuestado(){
    return this.formEncuestado.get('primerApellidoEncuestado');
  }

  get segundoApellidoEncuestado(){
    return this.formEncuestado.get('segundoApellidoEncuestado');
  }

  get estadoEncuestado(){
    return this.formEncuestado.get('estadoEncuestado');
  }

  constructor(private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm(){
    this.formEncuestado = this.formBuilder.group({
      primerNombreEncuestado: ['', [Validators.pattern(this.patronNombreEncuestado), Validators.required]],
      segundoNombreEncuestado: ['', [Validators.pattern(this.patronNombreEncuestado), Validators.required]],
      primerApellidoEncuestado: ['', [Validators.pattern(this.patronNombreEncuestado), Validators.required]],
      segundoApellidoEncuestado: ['', [Validators.pattern(this.patronNombreEncuestado), Validators.required]],
      estadoEncuestado: ['', Validators.required],
    });
  }

  ngOnInit(): void {
  }

  agregarEncuestado(){
    console.log('agregó encuestado');
  }

  onSubmit() {
    if (this.formEncuestado.valid) {
      console.log(this.formEncuestado.value);
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
  }
}
