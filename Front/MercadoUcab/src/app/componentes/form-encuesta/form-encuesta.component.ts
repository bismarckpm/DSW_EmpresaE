import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EncuestaService } from 'src/app/services/encuesta.service';
import { EstudioService } from '../../services/estudio.service';
import { TipoPreguntaService } from '../../services/tipo-pregunta.service';
import { PreguntaService } from '../../services/pregunta.service';
import { ToastService } from '../../services/toast.service';

@Component({
  selector: 'app-form-encuesta',
  templateUrl: './form-encuesta.component.html',
  styleUrls: ['./form-encuesta.component.css']
})
export class FormEncuestaComponent implements OnInit {

  @Input() encuesta = {
    _id: 0,
    estado: 'Activo',
    fechaInicio: '',
    fechaFin: '',
    estudio: {_id: 0},
    preguntas: []
  };
  @Input() preguntaEstudio = { };
  @Input() preguntaInsertar1 = {_id: 0};
  @Input() preguntaInsertar2 = {_id: 0};
  @Input() preguntaInsertar3 = {_id: 0};
  @Input() preguntaInsertar4 = {_id: 0};
  @Input() preguntaInsertar5 = {_id: 0};
  @Input() preguntaInsertar6 = {_id: 0};
  @Input() preguntaInsertar7 = {_id: 0};
  @Input() preguntaInsertar8 = {_id: 0};
  @Input() preguntaInsertar9 = {_id: 0};
  @Input() preguntaInsertar10 = {_id: 0};
  @Input() preguntaInsertar11 = {_id: 0};
  @Input() preguntaInsertar12 = {_id: 0};
  @Input() preguntaInsertar13 = {_id: 0};
  @Input() preguntaInsertar14 = {_id: 0};
  @Input() preguntaInsertar15 = {_id: 0};
  cantPreguntas = 0;
  listaPreguntasInsertar = [];
  preguntasMostrar: any = [];
  estudios: any = [];
  tipoPreguntas: any = [];
  opcionesCantidad: number[] = [5, 10, 15];
  sugerenciasPreguntas: any = [];

  /// PAra validar
  formEncuesta: FormGroup;
  patronFechaEstudio: any = /^\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$/;

  constructor(
    private servicio: EncuestaService,
    private servicioEstudio: EstudioService,
    private servicioTipoPregunta: TipoPreguntaService,
    private servicioPregunta: PreguntaService,
    private toast: ToastService,
    private formBuilder: FormBuilder
  ) {
    this.createForm();
    this.cargarEstudios();
    this.cargarTipoPreguntas();
  }

  ngOnInit(): void {
  }

  addEncuesta(): any{
    console.log(this.encuesta);
    if (this.formEncuesta.valid) {
      this.encuesta.preguntas = this.listaPreguntasInsertar;
      this.servicio.createEncuesta(this.encuesta).subscribe((data: {}) => {
        this.toast.showSuccess('La encuesta ha sido creada', 'Creada satisfactoriamente');
      },
      (error) => {
        this.toast.showError('Error de conexión', 'Intentelo más tarde');
      });
      this.encuesta = {
        _id: 0,
        estado: '',
        fechaInicio: '',
        fechaFin: '',
        estudio: {_id: 0},
        preguntas: []
      };
      location.reload();
    }
    else{
      this.toast.showError('Campos Incompletos', 'ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
  }

  // agregar pregunta a la lista de preguntas
  addPreguntaEncuesta(preguntaInsertar): void {
    if (preguntaInsertar._id !== 0){
      this.listaPreguntasInsertar.push(preguntaInsertar);
      console.log(this.listaPreguntasInsertar);
    }else {
      this.toast.showError('Seleccion incorrecta', 'Seleccione una pregunta válida');
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
    this.cantPreguntas = i;
  }

  sugerirPreguntas(): void{
    this.servicioPregunta.sugerirPreguntas(this.encuesta.estudio._id).subscribe((data: {}) => {
      this.sugerenciasPreguntas = data;
    });
  }

  limpiar(): void{
    this.preguntaInsertar1 = {_id: 0};
    this.preguntaInsertar2 = {_id: 0};
    this.preguntaInsertar3 = {_id: 0};
    this.preguntaInsertar4 = {_id: 0};
    this.preguntaInsertar5 = {_id: 0};
    this.preguntaInsertar6 = {_id: 0};
    this.preguntaInsertar7 = {_id: 0};
    this.preguntaInsertar8 = {_id: 0};
    this.preguntaInsertar9 = {_id: 0};
    this.preguntaInsertar10 = {_id: 0};
    this.preguntaInsertar11 = {_id: 0};
    this.preguntaInsertar12 = {_id: 0};
    this.preguntaInsertar13 = {_id: 0};
    this.preguntaInsertar14 = {_id: 0};
    this.preguntaInsertar15 = {_id: 0};
    this.cantPreguntas = 0;
  }

  ///// Metodos para las validaciones
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
      fechaInicioEncuesta: ['', [Validators.required, Validators.pattern(this.patronFechaEstudio)]],
      fechaFinEncuesta: ['', [ Validators.pattern(this.patronFechaEstudio)]],
      estudio: ['', Validators.required],
      pregunta: ['', Validators.required],
    });
  }

}
