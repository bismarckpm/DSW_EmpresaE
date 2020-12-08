import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Encuesta } from 'src/app/models/encuesta';
import { Estudio } from 'src/app/models/estudio';
import { EncuestaService } from 'src/app/services/encuesta.service';
import { EstudioService } from '../../services/estudio.service';
import { TipoPreguntaService } from '../../services/tipo-pregunta.service';

@Component({
  selector: 'app-form-encuesta',
  templateUrl: './form-encuesta.component.html',
  styleUrls: ['./form-encuesta.component.css']
})
export class FormEncuestaComponent implements OnInit {

  @Input() encuesta: Encuesta = {
    id: 0,
    estado: '',
    fechaInicio: '',
    fechaFin: '',
    dtoEstudio: null,
    dtoPregunta: null
  };
  cantPreguntas = [];
  estudios: any = [];
  tipoPreguntas: any = [];
  /// PAra validar
  formEncuesta: FormGroup;
  namePattern: any = /^[A-Za-z0-9\s]+$/;

  constructor(
    private servicio: EncuestaService,
    private servicioEstudio: EstudioService,
    private servicioTipoPregunta: TipoPreguntaService,
    private formBuilder: FormBuilder
  ) {
    this.createForm();
    this.servicioEstudio.getEstudios().subscribe((data: {}) => {
      this.estudios = data;
    });
    this.servicioTipoPregunta.getTipoPreguntas().subscribe((data: {}) => {
      this.tipoPreguntas = data;
    });
   }

  ngOnInit(): void {
  }

  addEncuesta(): any{

    if (!this.formEncuesta.valid) {
      this.servicio.createEncuesta(this.encuesta).subscribe((data: {}) => {
      });
    }
    else{
      console.log(this.encuesta);
      alert('FILL ALL FIELDS');
    }
  }

  asigCantPregunta(cant: number): void {
    for (let i = 0; i < cant; i++) {
      this.cantPreguntas.push(i);
    }
  }

  ///// Metodos para las validaciones
  get estadoEncuesta(): AbstractControl{
    return this.formEncuesta.get('estadoEncuesta');
  }

  createForm(): void{
    this.formEncuesta = this.formBuilder.group({
      estadoEncuesta: ['', Validators.required],
    });
  }

}
