import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Encuesta } from 'src/app/models/encuesta';
import { EncuestaService } from 'src/app/services/encuesta.service';
import { EstudioService } from '../../services/estudio.service';
import { TipoPreguntaService } from '../../services/tipo-pregunta.service';

@Component({
  selector: 'app-form-encuesta',
  templateUrl: './form-encuesta.component.html',
  styleUrls: ['./form-encuesta.component.css']
})
export class FormEncuestaComponent implements OnInit {

  @Input() encuesta = {
    _id: 0,
    estado: '',
    fechaInicio: '',
    fechaFin: '',
    estudio: {_id: 0},
    pregunta: [
      {descripcion: ''}
    ]
  };
  @Input() preguntas: string[] = [];
  cantPreguntas: number[] = [];
  estudios: any = [];
  tipoPreguntas: any = [];

  /// PAra validar
  formEncuesta: FormGroup;
  patronFechaEstudio: any = /^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$/;

  constructor(
    private servicio: EncuestaService,
    private servicioEstudio: EstudioService,
    private servicioTipoPregunta: TipoPreguntaService,
    private formBuilder: FormBuilder
  ) {
    this.createForm();
    this.cargarEstudios();
    this.cargarTipoPreguntas();
  }

  ngOnInit(): void {
  }

  addEncuesta(): any{

    if (this.formEncuesta.valid) {
      console.log(this.encuesta);
      this.servicio.createEncuesta(this.encuesta).subscribe((data: {}) => {
      });
      this.encuesta = {
        _id: 0,
        estado: '',
        fechaInicio: '',
        fechaFin: '',
        estudio: {_id: 0},
        pregunta: [
          {descripcion: ''}
        ]
      };
      // this.cargarEstudios();
      console.log(this.preguntas);
      alert('Agregó');
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }

  }

  asigCantPregunta(cant: number): void {
    for (let i = 0; i < cant; i++) {
      this.cantPreguntas.push(i);
    }
    for (let j = 0; j < cant; j++) {
      this.encuesta.pregunta.push();
    }
  }

  // tslint:disable-next-line: typedef
  cargarEstudios() {
    this.servicioEstudio.getEstudios().subscribe((data: {}) => {
      this.estudios = data;
    });
  }

  // tslint:disable-next-line: typedef
  cargarTipoPreguntas() {
    this.servicioTipoPregunta.getTipoPreguntas().subscribe((data: {}) => {
      this.tipoPreguntas = data;
    });
  }

  ///// Metodos para las validaciones
  get estadoEncuesta(): AbstractControl{
    return this.formEncuesta.get('estadoEncuesta');
  }

  get fechaInicioEncuesta(): AbstractControl{
    return this.formEncuesta.get('fechaInicioEncuesta');
  }

  get fechaFinEncuesta(): AbstractControl{
    return this.formEncuesta.get('fechaFinEncuesta');
  }

  get estudio(): AbstractControl{
    return this.formEncuesta.get('estudio');
  }

  get pregunta(): AbstractControl{
    return this.formEncuesta.get('pregunta');
  }

  createForm(): void{
    this.formEncuesta = this.formBuilder.group({
      estadoEncuesta: ['', Validators.required],
      fechaInicioEncuesta: ['', [Validators.required, Validators.pattern(this.patronFechaEstudio)]],
      fechaFinEncuesta: ['', [ Validators.pattern(this.patronFechaEstudio)]],
      estudio: ['', Validators.required],
      pregunta: ['', Validators.required],
    });
  }

}
