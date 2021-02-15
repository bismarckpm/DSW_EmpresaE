import {Component, Input, OnInit} from '@angular/core';
import {Estudio} from '../../../models/estudio';
import {EstudioService} from '../../../services/estudio.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Encuestado} from '../../../models/encuestado';
import * as Highcharts from 'highcharts';
import highcharts3D from 'highcharts/highcharts-3d.src';
import {PreguntaService} from '../../../services/pregunta.service';
import {EncuestaService} from '../../../services/encuesta.service';
import {Pregunta} from '../../../models/pregunta';
import { ToastService } from 'src/app/services/toast.service';
import {Opcion} from '../../../models/opcion';
// @ts-ignore
highcharts3D( Highcharts );

class Multiple { constructor(public _id: number){} }

@Component({
  selector: 'app-lista-estudios-analista',
  templateUrl: './lista-estudios-analista.component.html',
  styleUrls: ['./lista-estudios-analista.component.css']
})


export class ListaEstudiosAnalistaComponent implements OnInit {

  // Declaracion de variables
  prueba: number;
  prueba2: number;
  estudios: Estudio[] = [];
  preguntasEncuesta: Pregunta[] = [];
  opciones: Opcion[] = [];
  encuestados: Encuestado[] = [];
  _id = this.actRoute.snapshot.params._id;
  @Input() analistaData = {_id: 0, comentarioAnalista: '', estado: '', fechaFin: ''};
  formEstudioAnalista: FormGroup;

  infoGraficos: any = [];
  chartOptions: Highcharts.Options[] = [];
  highcharts = Highcharts;
  aux:any=[];

  patronFechaEstudio: any = /^\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$/;
  get fechaFinEstudio(){return this.formEstudioAnalista.get('fechaFinEstudio'); }

  // variable a asignar a la clave del objeto a devolver
  listaRespuestas: any = [];
  listaOpciones: any = [];

  respuestaAPregunta: any = {
    // id de la pregunta a responder
    _id: 0,
    texto: '',
    opciones: []
  };

  // objeto devolver en el post
  respuestaEncuesta = {
    usuario: 0,
    estudio: 0,
    respuestas: []
  };

  @Input() respuestaDOpcion = {_id: 0};
  @Input() respuestaAbierta = '';
  @Input() respuestaMultiple = [];

  @Input() respuestas: any = {
    opcionesVF: [],
    opcionesRg: [],
    opcionesMulti: [],
    opcionesSimple: [],
    textos: ['prueba1', 'prueba2']
  };

  constructor(
    public estudioService: EstudioService,
    private preguntaService: PreguntaService,
    private encuestaService: EncuestaService,
    private formBuilder: FormBuilder,
    public actRoute: ActivatedRoute,
    public toast: ToastService,
    public router: Router

  ) { this.createForm(); }



  ngOnInit(): void {
    this.loadEstudios();
  }

  editar(estudio){
    this.analistaData = estudio;
  }

  Comenzar(){
    this.analistaData.estado='en ejecucion';
    console.log(this.analistaData);

    this.estudioService.updateEstudio(this.analistaData._id, this.analistaData).subscribe(data => {
      this.aux=data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
    });
  }

  Terminar(){
    this.analistaData.estado='culminado';
    console.log(this.analistaData);
    this.estudioService.updateEstudio(this.analistaData._id, this.analistaData).subscribe(data => {
      this.aux=data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
    });
  }


  addRespuestasEncuesta(): void{
    this.respuestaEncuesta.respuestas = this.listaRespuestas;
    this.respuestaEncuesta.usuario = 32 ;     //JSON.parse(localStorage.getItem('usuarioID'))
    console.log('JSON a enviar....');
    console.log(this.respuestaEncuesta);
    this.encuestaService.createRespuestaEncuesta(this.respuestaEncuesta).subscribe((data) => {});
  }

  addRespuestasEncuestaxAnalista(): void{
    this.respuestaEncuesta.respuestas = this.listaRespuestas;
    this.respuestaEncuesta.usuario = this.prueba2 ;     //JSON.parse(localStorage.getItem('usuarioID'))
    console.log('JSON a enviar....');
    console.log(this.respuestaEncuesta);
    this.encuestaService.createRespuestaEncuestaxAnalista(this.respuestaEncuesta).subscribe((data) => {});
  }

  loadPreguntasResponder(idEstudio: number): void{                      //DEBE SER DIFERENTE EL NOMBRE, Y QUE SEA EL ID DEL ENCUESTADO NO DEL USUARIO
    // aqui esta el get en preguntas pasandole el id del estudio
    const idUsuario = JSON.parse(localStorage.getItem('usuarioID')); //OJOOOOOOOOOOOOOOOOOOO ESE ID NO ESSSSSSSSSS

    this.preguntaService.getPreguntasResponder(idEstudio, idUsuario)
      .subscribe((data) => {
        this.preguntasEncuesta = data;
      });
    this.respuestaEncuesta.estudio = idEstudio;
  }

  loadPreguntasResponderxAnalista(idEncuestado: number): void{                      //DEBE SER DIFERENTE EL NOMBRE, Y QUE SEA EL ID DEL ENCUESTADO NO DEL USUARIO
    // aqui esta el get en preguntas pasandole el id del estudio
    this.prueba2 = idEncuestado;                  //este es el id del encuestado
    this.preguntaService.getPreguntasResponderxAnalista(this.prueba, idEncuestado) //this.prueba es el id del estudio
      .subscribe((data) => {
        this.preguntasEncuesta = data;
      });
    this.respuestaEncuesta.estudio = this.prueba; //aqui estaba un "idestudio" que elimine
  }

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

  loadEstudios(): void {
    const user = JSON.parse(localStorage.getItem('usuarioID'));
    this.estudioService.getEstudioAnalista(user).subscribe(data => {
      this.aux=data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
        this.estudios = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
    });
  }

  loadDataMuestra(id): void {
    this.prueba = id;
    this.estudioService.getDataMuestraxAnalista(id).subscribe(data => {
      this.aux=data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
        this.encuestados = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
    });
  }

  updateEstudio(){
    this.estudioService.updateEstudio(this.analistaData._id, this.analistaData).subscribe(data => {
    });
    this.loadEstudios();
  }

  loadInfoGraficos(estudio): void {
    this.infoGraficos = [];
    this.chartOptions = [];
    this.estudioService.getDataGraficos(estudio).subscribe(data => {
      this.aux=data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
        this.infoGraficos = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
      this.agregarDatos();
      console.log(this.infoGraficos);
    });
  }

  agregarDatos() {
    this.infoGraficos.forEach(element => {
      const valor = element.opcionesResultado.map((x: any) => {
        return { name: x.descripcion, y: x.valor };
      });
      const enunciado = element.descripcion;

      this.chartOptions.push(this.chart(enunciado, valor));
    });
  }

  chart(enunciado: any, valor: any): Highcharts.Options {
    const chartOptions: Highcharts.Options = {
      chart: {
        type: 'pie',
        plotShadow: false,
        options3d: {
          enabled: true,
          alpha: 45,
          beta: 0
        }
      },
      title: {
        text: enunciado
      },
      tooltip: {
        headerFormat: '',
        pointFormat:
          '<span style=\'color:{point.color}\'>\u25CF</span> {point.name}: <b>{point.y}</b>',
        style: {
          fontSize: '10px'
        }
      },
      plotOptions: {
        pie: {
          allowPointSelect: true,
          cursor: 'pointer',
          depth: 35,
          shadow: true,
          innerSize: '20%',
          dataLabels: {
            enabled: false
          },
          showInLegend: true
        }
      },
      series: [
        {
          type: 'pie',
          data: valor
        }
      ]
    };
    return chartOptions;
  }

  get comentarioAnalistaEstudio(){ return this.formEstudioAnalista.get('comentarioAnalistaEstudio'); }
  get estadoEstudioAnalista(){ return this.formEstudioAnalista.get('estadoEstudioAnalista'); }

  createForm() {
    this.formEstudioAnalista = this.formBuilder.group({
      comentarioAnalistaEstudio: ['', [Validators.required, Validators.maxLength(100)]],
      estadoEstudioAnalista: ['', Validators.required],
      fechaFinEstudio: ['', [ Validators.pattern(this.patronFechaEstudio)]],
    });
  }
}
