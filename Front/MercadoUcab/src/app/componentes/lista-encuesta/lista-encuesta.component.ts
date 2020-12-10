import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Encuesta } from 'src/app/models/encuesta';
import { EstudioService } from 'src/app/services/estudio.service';
import { TipoPreguntaService } from 'src/app/services/tipo-pregunta.service';
import { EncuestaService } from '../../services/encuesta.service';

@Component({
  selector: 'app-lista-encuesta',
  templateUrl: './lista-encuesta.component.html',
  styleUrls: ['./lista-encuesta.component.css']
})
export class ListaEncuestaComponent implements OnInit {

  tipoPreguntas: any = [];
  cantPreguntas = [];
  encuestas: any = [];
  estudios: any = [];
  _id = this.actRoute.snapshot.params._id;

  formEncuesta: FormGroup;
  patronFechaEstudio: any = /^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$/;
  patronNombreEstudio: any = /^[A-Za-z\s]+$/;

  @Input() encuestaData = {
    _id: 0,
    estado: '',
    fechaInicio: '',
    fechaFin: '',
    estudio: {_id: 0},
    pregunta: [
      {descripcion: ''}
    ]
  };

  constructor(
    private servicio: EncuestaService,
    private servicioEstudio: EstudioService,
    private servicioTipoPregunta: TipoPreguntaService,
    private formBuilder: FormBuilder,
    public actRoute: ActivatedRoute,
  ) {
    this.createForm();
    this.loadEncuestas();
    this.cargarEstudios();
    this.cargarTipoPreguntas();
  }

  ngOnInit(): void {
  }

  loadEncuestas(): any{
    return this.servicio.getEncuestas().subscribe((data: {}) => {
      this.encuestas = data;
    });
  }

  // Delete Encuesta
  deleteEncuesta(id): any {
    if (window.confirm('Are you sure, you want to delete?')){
      this.servicio.deleteEncuesta(id).subscribe(data => {
        this.loadEncuestas();
      });
    }
  }

  updateEncuesta(id): any {
    if (this.formEncuesta.valid) {
      this.servicio.updateEncuesta(this.encuestaData._id, this.encuestaData).subscribe(data => {
       });
      this.loadEncuestas();
      }
      else{
        alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
      }
  }
  // Sustitucion de variables para el update
  editar(encuesta): void{
    this.cargarEstudios();
    this.cargarTipoPreguntas();
    this.encuestaData = encuesta;
  }

  asigCantPregunta(cant: number): void {
    for (let i = 0; i < cant; i++) {
      this.cantPreguntas.push(i);
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

  get fechaInicioEstudio(): AbstractControl{
    return this.formEncuesta.get('fechaInicioEstudio');
  }

  get fechaFinEstudio(): AbstractControl{
    return this.formEncuesta.get('fechaFinEstudio');
  }

  get nombreEstudio(): AbstractControl{
    return this.formEncuesta.get('estudio');
  }

  createForm(): void{
    this.formEncuesta = this.formBuilder.group({
      estadoEncuesta: ['', Validators.required],
      fechaInicioEstudio: ['', [Validators.required, Validators.pattern(this.patronFechaEstudio)]],
      fechaFinEstudio: ['', [ Validators.pattern(this.patronFechaEstudio)]],
      estudio: ['', Validators.required],
    });
  }
}
