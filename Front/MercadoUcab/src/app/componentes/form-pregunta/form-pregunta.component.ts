import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-form-pregunta',
  templateUrl: './form-pregunta.component.html',
  styleUrls: ['./form-pregunta.component.css']
})
export class FormPreguntaComponent implements OnInit {

  formPregunta: FormGroup;
  patronDescripcionPregunta: any = /^[A-Za-z0-9\s]+$/;

  get descripcionPregunta(){
    return this.formPregunta.get('descripcionPregunta');
  }

  get estadoPregunta(){
    return this.formPregunta.get('estadoPregunta');
  }

  constructor(private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm(){
    this.formPregunta = this.formBuilder.group({
      descripcionPregunta: ['', [Validators.required, Validators.pattern(this.patronDescripcionPregunta)]],
      estadoPregunta: ['', Validators.required],
    });
  }

  ngOnInit(): void {
  }

  agregarPregunta(){
    console.log('agregó pregunta');
  }

  onSubmit() {
    if (this.formPregunta.valid) {
      console.log(this.formPregunta.value);
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
  }
}
