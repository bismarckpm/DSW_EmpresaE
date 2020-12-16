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
  encuestas: Encuesta[] = [];
  _id = this.actRoute.snapshot.params._id;
  cantPreguntas = [];
  estudios: any = [];
  tipoPreguntas: any = [];

  // Declaracion para validar
  formEncuesta: FormGroup;
  patronFechaEncuesta: any = /^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$/;

  constructor(
    private servicio: EncuestaService,
    private servicioEstudio: EstudioService,
    private servicioTipoPregunta: TipoPreguntaService,
    private formBuilder: FormBuilder,
    public actRoute: ActivatedRoute,
  ) {
    this.createForm();
  }

  ngOnInit(): void {
    this.loadEncuestas();
    this.loadEstudios();
    this.loadTipoPreguntas();
  }

  loadEncuestas(): void {
    this.servicio.getEncuestas().subscribe(data => {
      this.encuestas = data;
    });
  }

  loadEstudios(): void {
    this.servicioEstudio.getEstudios().subscribe((data: {}) => {
      this.estudios = data;
    });
  }

  loadTipoPreguntas(): void {
    this.servicioTipoPregunta.getTipoPreguntas().subscribe((data: {}) => {
      this.tipoPreguntas = data;
    });
  }

  // Delete Encuesta
  deleteEncuesta(id: number): void {
    if (window.confirm('Are you sure, you want to delete?')){
      this.servicio.deleteEncuesta(id).subscribe(data => {
        this.loadEncuestas();
      });
    }
  }

  updateEncuesta(): void {
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
    this.loadEstudios();
    this.loadTipoPreguntas();
    this.encuestaData = encuesta;
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
      fechaInicioEncuesta: ['', [Validators.required, Validators.pattern(this.patronFechaEncuesta)]],
      fechaFinEncuesta: ['', [ Validators.pattern(this.patronFechaEncuesta)]],
      estudio: ['', Validators.required],
      pregunta: ['', Validators.required],
    });
  }

}
