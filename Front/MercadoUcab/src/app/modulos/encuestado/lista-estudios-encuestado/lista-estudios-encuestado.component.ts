import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {EstudioService} from '../../../services/estudio.service';
import { PreguntaService } from 'src/app/services/pregunta.service';
import { Estudio } from '../../../models/estudio';
import { Pregunta } from '../../../models/pregunta';
import { TipoPregunta } from '../../../models/tipo-pregunta';
import { Opcion } from '../../../models/opcion';
import { EncuestaService } from 'src/app/services/encuesta.service';

class Multiple { constructor(public _id: number){} }

@Component({
  selector: 'app-lista-estudios-encuestado',
  templateUrl: './lista-estudios-encuestado.component.html',
  styleUrls: ['./lista-estudios-encuestado.component.css']
})

export class ListaEstudiosEncuestadoComponent implements OnInit {

  estudios: Estudio[] = [];
  preguntasEncuesta: Pregunta[] = [];
  opciones: Opcion[] = [];
  formEstudioEncuestado: FormGroup;

  // objeto individual, cada pregunta que sera agregada a la lista de respuesta
  // esa lista de respuesta será agregada a JSON general en la clave "respuestas"
  respuestaAPregunta: any = {
    // id de la pregunta a responder
    _id: 0,
    texto: '',
    opciones: []
  };

  @Input() respuestas: any = {
    opcionesVF: [],
    opcionesRg: [],
    opcionesMulti: [],
    opcionesSimple: [],
    textos: ['prueba1', 'prueba2']
  };

  // objeto para registrar la respuesta seleccionada segun el tipo de pregunta
  @Input() respuestaDOpcion = {_id: 0};
  @Input() respuestaAbierta = '';
  @Input() respuestaMultiple = [];

  // variable a asignar a la clave del objeto a devolver
  listaRespuestas: any = [];
  listaOpciones: any = [];

  // objeto devolver en el post
  respuestaEncuesta = {
    usuario: 0,
    estudio: 0,
    respuestas: []
  };

  constructor(
    public estudioService: EstudioService,
    private preguntaService: PreguntaService,
    private encuestaService: EncuestaService,
    private formBuilder: FormBuilder,
    public actRoute: ActivatedRoute,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.loadEstudios();
  }

  addRespuestasEncuesta(): void{
    this.respuestaEncuesta.respuestas = this.listaRespuestas;
    this.respuestaEncuesta.usuario = JSON.parse(localStorage.getItem('usuarioID'));
    console.log('JSON a enviar....');
    console.log(this.respuestaEncuesta);
    this.encuestaService.createRespuestaEncuesta(this.respuestaEncuesta).subscribe((data) => {});
  }

  loadEstudios(): void{
    const id = JSON.parse(localStorage.getItem('usuarioID'));
    this.estudioService.getEstudioEncuestado(id).subscribe(data => {
      this.estudios = data;
    });
  }

  loadPreguntasResponder(idEstudio: number): void{
    // aqui esta el get en preguntas pasandole el id del estudio
    this.preguntaService.getPreguntasResponder(idEstudio)
        .subscribe((data) => {
          this.preguntasEncuesta = data;
        });
    this.respuestaEncuesta.estudio = idEstudio;
  }

  // capto las opciones que seleccionó el usuario
  //  esta lista se agrega al objeto respuesta(individual)
  addOpcionesRespuesta(idPregunta): void {
    this.listaOpciones.push(this.respuestaDOpcion);
    this.respuestaDOpcion = {_id: 0};
    this.respuestaAPregunta._id = idPregunta;
    this.respuestaAPregunta.opciones = this.listaOpciones;
    this.listaOpciones = [];
    console.log('objeto respuesta a pregunta');
    console.log(this.respuestaAPregunta);
    console.log('lista de preguntas a enviar');
    this.listaRespuestas.push(this.respuestaAPregunta);
    console.log(this.listaRespuestas);
    this.respuestaAPregunta = {
      _id: 0,
      texto: '',
      opciones: []
    };
  }

  addRespuestaAbierta(idPregunta): void {
    this.respuestaAPregunta.texto = this.respuestaAbierta;
    this.respuestaAbierta = '';
    this.respuestaAPregunta._id = idPregunta;
    console.log('objeto respuesta a pregunta');
    console.log(this.respuestaAPregunta);
    console.log('lista de preguntas a enviar');
    this.listaRespuestas.push(this.respuestaAPregunta);
    console.log(this.listaRespuestas);
    this.respuestaAPregunta = {
      _id: 0,
      texto: '',
      opciones: []
    };
  }

  addRespuestaMultiple(idPregunta): void {
    for (const resp of this.respuestaMultiple){
      this.listaOpciones.push(new Multiple(resp));
    }
    console.log(this.listaOpciones);
    this.respuestaAPregunta._id = idPregunta;
    this.respuestaAPregunta.opciones = this.listaOpciones;
    this.listaOpciones = [];
    console.log('objeto respuesta a pregunta');
    console.log(this.respuestaAPregunta);
    console.log('lista de preguntas a enviar');
    this.listaRespuestas.push(this.respuestaAPregunta);
    console.log(this.listaRespuestas);
    this.respuestaAPregunta = {
      _id: 0,
      texto: '',
      opciones: []
    };
  }
}
