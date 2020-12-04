import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-form-usuarios',
  templateUrl: './form-usuarios.component.html',
  styleUrls: ['./form-usuarios.component.css']
})
export class FormUsuariosComponent implements OnInit {

  formUsuario: FormGroup;
  patronNombreUsuario: any = /^[a-zA-Z]+$/;

  get primerNombreUsuario(){
    return this.formUsuario.get('primerNombreUsuario');
  }

  get segundoNombreUsuario(){
    return this.formUsuario.get('segundoNombreUsuario');
  }

  get primerApellidoUsuario(){
    return this.formUsuario.get('primerApellidoUsuario');
  }

  get segundoApellidoUsuario(){
    return this.formUsuario.get('segundoApellidoUsuario');
  }

  get estadoUsuario(){
    return this.formUsuario.get('estadoUsuario');
  }

  constructor(private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm(){
    this.formUsuario = this.formBuilder.group({
      primerNombreUsuario: ['', [Validators.pattern(this.patronNombreUsuario), Validators.required]],
      segundoNombreUsuario: ['', [Validators.pattern(this.patronNombreUsuario), Validators.required]],
      primerApellidoUsuario: ['', [Validators.pattern(this.patronNombreUsuario), Validators.required]],
      segundoApellidoUsuario: ['', [Validators.pattern(this.patronNombreUsuario), Validators.required]],
      estadoUsuario: ['', Validators.required],
    });
  }

  ngOnInit(): void {
  }

  agregarUsuario(){
    console.log('agregó usuario');
  }

  onSubmit() {
    if (this.formUsuario.valid) {
      console.log(this.formUsuario.value);
    }
    else{
      alert('FILL ALL FIELDS');
    }
  }
}
