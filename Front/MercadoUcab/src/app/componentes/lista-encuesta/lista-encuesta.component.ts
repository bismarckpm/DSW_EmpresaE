import { Component, Input, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
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
  formEncuesta: FormGroup;
  namePattern: any = /^[A-Za-z0-9\s]+$/;
  @Input() encuesta: Encuesta = {
    _id: 0,
    estado: '',
    fechaInicio: '',
    fechaFin: '',
    dtoEstudio: null,
    dtoPregunta: null
  };

  constructor(
    private servicio: EncuestaService,
    private servicioEstudio: EstudioService,
    private servicioTipoPregunta: TipoPreguntaService,
    private formBuilder: FormBuilder
  ) {
    this.loadEncuestas();
    this.servicioEstudio.getEstudios().subscribe((data: {}) => {
      this.estudios = data;
    });
    this.servicioTipoPregunta.getTipoPreguntas().subscribe((data: {}) => {
      this.tipoPreguntas = data;
    });
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

  updateEncuesta() {

  }
  // Sustitucion de variables para el update
  editar(encuesta){
  }

  asigCantPregunta(cant: number): void {
    for (let i = 0; i < cant; i++) {
      this.cantPreguntas.push(i);
    }
  }

  ///// Metodos para las validaciones
  get nombreEncuesta(): AbstractControl{
    return this.formEncuesta.get('nombreEncuesta');
  }

  get estadoEncuesta(): AbstractControl{
    return this.formEncuesta.get('estadoEncuesta');
  }

  createForm(): any {
    this.formEncuesta = this.formBuilder.group({
      nombreEncuesta: ['', [Validators.pattern(this.namePattern), Validators.required]],
      estadoEncuesta: ['', Validators.required],
    });
  }
}
