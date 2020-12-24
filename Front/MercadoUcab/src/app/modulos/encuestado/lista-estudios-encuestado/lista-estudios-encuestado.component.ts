import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {EstudioService} from '../../../services/estudio.service';
import {EncuestaService} from '../../../services/encuesta.service';
import { PreguntaService } from 'src/app/services/pregunta.service';
import { Estudio } from '../../../models/estudio';
import { Encuesta } from '../../../models/encuesta';
import { Pregunta } from '../../../models/pregunta';
import { TipoPregunta } from '../../../models/tipo-pregunta';
import { Opcion } from '../../../models/opcion';

@Component({
  selector: 'app-lista-estudios-encuestado',
  templateUrl: './lista-estudios-encuestado.component.html',
  styleUrls: ['./lista-estudios-encuestado.component.css']
})
export class ListaEstudiosEncuestadoComponent implements OnInit {

  estudios: Estudio[] = [];
  // encuestas: Encuesta[] = [];
  preguntasEncuesta: Pregunta[] = [];
  opciones: Opcion[] = [];
  tipos: TipoPregunta[] = [];
  _id = this.actRoute.snapshot.params._id;
  formEstudioEncuestado: FormGroup;
  presiono = 0;

  // no sé para qué es este objeto
  // @Input() encuestadoData = {};

  // objeto individual, cada pregunta que sera agregada a la lista de respuesta
  // esa lista de respuesta será agregada a JSON general en la clave "respuestas"
  @Input() respuestaAPregunta: any = {
    // id de la pregunta a responder
    _id: 0,
    respuesta: {
      texto: '',
      opciones: []
    }
  };

  // varibale a asignar a la clave del objeto a devolver
  listaRespuestas: any = [];
  listaOpciones: any = [];

  // objeto para registrar la opcion seleccionada
  @Input() respuestaDOpcion = {_id: 0};

  // objeto devolver en el post
  respuestaEncuesta = {
    respuestas: []
  };

  constructor(
    public estudioService: EstudioService,
    // public encuestaService: EncuestaService,
    private preguntaService: PreguntaService,
    private formBuilder: FormBuilder,
    public actRoute: ActivatedRoute,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.loadEstudios();
  }

  addEncuesRespuestaEncuesta(): void{
    this.respuestaEncuesta.respuestas = this.listaRespuestas;

  }

  loadEstudios(): void{
    const id = JSON.parse(localStorage.getItem('usuarioID'));
    this.estudioService.getEstudioEncuestado(id).subscribe(data => {
      this.estudios = data;
    });
  }

  loadPreguntasResponder(idEstduio: number): void{
    // aqui esta el get en preguntas pasandole el id del estudio
    this.preguntaService.getPreguntasResponder(idEstduio)
        .subscribe((data) => {
          this.preguntasEncuesta = data;
          this.extraerOpciones(0);
        });
  }

  // posiblemente no se vaya a usar
  // extraerOpciones(idPregunta: number): void {
  //   this.opciones = this.preguntasEncuesta[idPregunta].opciones;
  //   console.log(this.opciones);
  // }

  // capto las opciones que seleccionó el usuario
  //  esta lista se agrega al objeto respuesta(individual)
  addOpcionesRespuesta(idPregunta): void {
    this.listaOpciones.push(this.respuestaDOpcion);
    console.log('opcion seleccionada');
    console.log(this.listaOpciones);
    this.respuestaDOpcion = {_id: 0};
    this.respuestaAPregunta._id = idPregunta;
    this.respuestaAPregunta.respuesta.opciones = this.listaOpciones;
    this.listaOpciones = [];
    console.log('objeto pregunta');
    console.log(this.respuestaAPregunta);
    console.log('lista de preguntas a enviar');
    this.listaRespuestas.push(this.respuestaAPregunta);
    console.log(this.listaRespuestas);
    this.respuestaAPregunta = {
      _id: 0,
      respuesta: {
        texto: '',
        opciones: []
      }
    };
  }


  // estos dos metodos no sé para qué son
  // loadEncuesta(): void{
  //   const id = JSON.parse(localStorage.getItem('estudioID'));
  //   this.encuestaService.getEncuestaEstudio(id).subscribe(data => {
  //     this.encuestas = data;
  //   });
  // }

  // loadPreguntas(): void{
  //   const id = JSON.parse(localStorage.getItem('estudioID'));
  //   this.encuestaService.getEncuestaEstudio(id).subscribe(data => {
  //     this.encuestas = data;
  //   });
  // }

}
