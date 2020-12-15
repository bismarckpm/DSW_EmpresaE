import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Encuesta } from 'src/app/models/encuesta';
import { EncuestaService } from 'src/app/services/encuesta.service';
import { EstudioService } from '../../services/estudio.service';
import { TipoPreguntaService } from '../../services/tipo-pregunta.service';
import { PreguntaService } from '../../services/pregunta.service';
import { Pregunta } from 'src/app/models/pregunta';

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
      { descripcion: '', tipo: '' }
    ]
  };
  @Input() preguntaEstudio = { };
  @Input() preguntaInsertar = {_id: 0};
  listaPreguntasInsertar = [];
  preguntasMostrar: any = [];
  estudios: any = [];
  tipoPreguntas: any = [];
  cantPreguntas: number[] = [];

  /// PAra validar
  formEncuesta: FormGroup;
  patronFechaEstudio: any = /^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$/;

  constructor(
    private servicio: EncuestaService,
    private servicioEstudio: EstudioService,
    private servicioTipoPregunta: TipoPreguntaService,
    private servicioPregunta: PreguntaService,
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
          {descripcion: '', tipo: ''}
        ]
      };
      // this.cargarEstudios();
      console.log(this.preguntaInsertar);
      alert('Agregó');
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }

  }

  addPreguntaEstudio(): void {
    this.listaPreguntasInsertar.push(this.preguntaInsertar);
    console.log(this.listaPreguntasInsertar);
    this.preguntaInsertar = {_id: 0};
  }

  asigCantPregunta(cant: number): void {
    for (let i = 0; i < cant; i++) {
      this.cantPreguntas.push(i);
    }
    for (let j = 0; j < cant; j++) {
      this.encuesta.pregunta.push();
    }
  }

  ////// cargar de servicios
  cargarEstudios(): void {
    this.servicioEstudio.getEstudios().subscribe((data: {}) => {
      this.estudios = data;
    });
  }

  cargarTipoPreguntas(): void {
    this.servicioTipoPregunta.getTipoPreguntas().subscribe((data: {}) => {
      this.tipoPreguntas = data;
    });
  }

  cargarPreguntas(): void {
    this.servicioPregunta.getPreguntasXSubcategoria(this.encuesta.estudio._id).subscribe((data: {}) => {
      this.preguntasMostrar = data;
    });
    console.log(this.preguntasMostrar);
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
