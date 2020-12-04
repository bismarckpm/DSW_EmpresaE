import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-form-presentacion',
  templateUrl: './form-presentacion.component.html',
  styleUrls: ['./form-presentacion.component.css']
})
export class FormPresentacionComponent implements OnInit {

  formPresentacion: FormGroup;
  patronDescripcionPresentacion: any = /^[A-Za-z0-9\s]+$/;

  get descripcionPresentacion(){
    return this.formPresentacion.get('descripcionPresentacion');
  }

  get estadoPresentacion(){
    return this.formPresentacion.get('estadoPresentacion');
  }

  constructor(private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm(){
    this.formPresentacion = this.formBuilder.group({
      descripcionPresentacion: ['', [Validators.required, Validators.pattern(this.patronDescripcionPresentacion)]],
      estadoPresentacion: ['', Validators.required],
    });
  }

  ngOnInit(): void {
  }

  agregarPresentacion(){
    console.log('agregó presentacion');
  }

  onSubmit() {
    if (this.formPresentacion.valid) {
      console.log(this.formPresentacion.value);
    }
    else{
      alert('FILL ALL FIELDS');
    }
  }
}
