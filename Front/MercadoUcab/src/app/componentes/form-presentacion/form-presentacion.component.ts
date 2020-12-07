import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { PresentacionService } from 'src/app/services/presentacion.service';

@Component({
  selector: 'app-form-presentacion',
  templateUrl: './form-presentacion.component.html',
  styleUrls: ['./form-presentacion.component.css']
})
export class FormPresentacionComponent implements OnInit {


  @Input() presentacion ={ id:0, descripcion:'',estado:''};
  // Validacion de datos
  formPresentacion: FormGroup;
  namePattern: any = /^[A-Za-z0-9\s]+$/;

  get descripcionPresentacion(){
    return this.formPresentacion.get('descripcionPresentacion');
  }

  get estadoPresentacion(){
    return this.formPresentacion.get('estadoPresentacion');
  }

  constructor(
    public presentacionService:PresentacionService,
    private formBuilder: FormBuilder,
    public router:Router,

    ) {this.createForm();
  }
  
  ngOnInit(): void {
  }

  addPresentacion(){
    if (this.formPresentacion.valid) {
    this.presentacionService.createPresentacion(this.presentacion).subscribe((data: {}) => {
    })
  }
  else{
    alert(' LLenar todos los campos por favor');
  }
  }

// Validaciones
  createForm(){
    this.formPresentacion = this.formBuilder.group({
      descripcionPresentacion: ['', [Validators.required, Validators.pattern(this.namePattern)]],
      estadoPresentacion: ['', Validators.required],
    });
  }





 
}
