import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EncuestaService } from 'src/app/services/encuesta.service';
import { EstudioService } from '../../services/estudio.service';
import { TipoPreguntaService } from '../../services/tipo-pregunta.service';
import { PreguntaService } from '../../services/pregunta.service';

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
    preguntas: []
  };
  @Input() preguntaEstudio = { };
  @Input() preguntaInsertar = {_id: 0};
  listaPreguntasInsertar = [];
  preguntasMostrar: any = [];
  estudios: any = [];
  tipoPreguntas: any = [];
  opcionesCantidad: number[] = [5, 10, 15];
  auxIterador = [];

  /// PAra validar
  formEncuesta: FormGroup;
  patronFechaEstudio: any = /^\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$/;

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
      this.encuesta.preguntas = this.listaPreguntasInsertar;
      this.servicio.createEncuesta(this.encuesta).subscribe((data: {}) => {
      });
      this.encuesta = {
        _id: 0,
        estado: '',
        fechaInicio: '',
        fechaFin: '',
        estudio: {_id: 0},
        preguntas: []
      };
      this.auxIterador = [];
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }

    location.reload();

  }

  // agregar pregunta a la lista de preguntas
  addPreguntaEncuesta(): void {
    this.listaPreguntasInsertar.push(this.preguntaInsertar);
    console.log(this.listaPreguntasInsertar);
    this.preguntaInsertar = {_id: 0};
  }

  asigCantPregunta(cant: number): void {
    for (let i = 0; i < cant; i++) {
      this.auxIterador.push(i);
    }
  }

  ////// cargar de servicios
  cargarEstudios(): void {
    this.servicioEstudio.getEstudiosSinEncuesta().subscribe((data: {}) => {
      this.estudios = data;
    });
  }

  cargarTipoPreguntas(): void {
    this.servicioTipoPregunta.getTipoPreguntas().subscribe((data: {}) => {
      this.tipoPreguntas = data;
    });
  }

  // aqui yo mando el id del estudio para que me devuelva una lista de preguntas
  // asociadas a la subcategoria del estudio
  cargarPreguntas(i: number): void {
    this.servicioPregunta.getPreguntasXSubcategoria(this.encuesta.estudio._id).subscribe((data: {}) => {
      this.preguntasMostrar = data;
    });
    this.auxIterador = [];
    this.asigCantPregunta(i);
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
