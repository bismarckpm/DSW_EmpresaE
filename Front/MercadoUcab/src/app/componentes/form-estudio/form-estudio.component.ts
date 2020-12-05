import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-form-estudio',
  templateUrl: './form-estudio.component.html',
  styleUrls: ['./form-estudio.component.css']
})
export class FormEstudioComponent implements OnInit {

  formEstudio: FormGroup;
  patronEdadEstudio: any = /^(0?[0-9]{1,2}|1[0-7][0-9]|99)$/;
  patronFechaEstudio: any = /^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$/;
  patronNombreEstudio: any = /^[A-Za-z\s]+$/;

  get nombreEstudio(){
    return this.formEstudio.get('nombreEstudio');
  }

  get comentarioAnalistaEstudio(){
    return this.formEstudio.get('comentarioAnalistaEstudio');
  }

  get edadMinimaEstudio(){
    return this.formEstudio.get('edadMinimaEstudio');
  }

  get edadMaximaEstudio(){
    return this.formEstudio.get('edadMaximaEstudio');
  }

  get estadoEstudio(){
    return this.formEstudio.get('estadoEstudio');
  }

  get fechaInicioEstudio(){
    return this.formEstudio.get('fechaInicioEstudio');
  }

  get fechaFinEstudio(){
    return this.formEstudio.get('fechaFinEstudio');
  }

  constructor(private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm(){
    this.formEstudio = this.formBuilder.group({
      nombreEstudio: ['', [Validators.required, Validators.pattern(this.patronNombreEstudio)]],
      estadoEstudio: ['', Validators.required],
      fechaInicioEstudio: ['', [Validators.required, Validators.pattern(this.patronFechaEstudio)]],
      fechaFinEstudio: ['', [Validators.required, Validators.pattern(this.patronFechaEstudio)]],
      edadMinimaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      edadMaximaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      comentarioAnalistaEstudio: ['', Validators.required],

    });
  }

  ngOnInit(): void {
  }

  agregarEstudio(){
    console.log('agregó estudio');
  }

  onSubmit() {
    if (this.formEstudio.valid) {
      console.log(this.formEstudio.value);
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
  }

}
