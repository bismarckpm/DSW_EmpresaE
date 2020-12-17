import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {EstudioService} from '../../../services/estudio.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Estudio} from '../../../models/estudio';
import {EncuestaService} from '../../../services/encuesta.service';
import {Encuesta} from '../../../models/encuesta';
import { PreguntaService } from 'src/app/services/pregunta.service';

@Component({
  selector: 'app-lista-estudios-encuestado',
  templateUrl: './lista-estudios-encuestado.component.html',
  styleUrls: ['./lista-estudios-encuestado.component.css']
})
export class ListaEstudiosEncuestadoComponent implements OnInit {

  estudios: Estudio[] = [];
  encuestas: Encuesta[] = [];
  preguntasEncuesta: any = [];
  opciones: any = [];
  _id = this.actRoute.snapshot.params._id;
  @Input() encuestadoData = {};
  formEstudioEncuestado: FormGroup;

  constructor(
    public estudioService: EstudioService,
    public encuestaService: EncuestaService,
    private preguntaService: PreguntaService,
    private formBuilder: FormBuilder,
    public actRoute: ActivatedRoute,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.loadEstudios();
  }

  loadEstudios(): void{
    let id = JSON.parse(localStorage.getItem('usuarioID'));
    this.estudioService.getEstudioEncuestado(id).subscribe(data => {
      this.estudios = data;
    });
  }

  loadPreguntasResponder(idEstduio: number): void{
    this.preguntaService.getPreguntasResponder(idEstduio).subscribe(data => {
      this.preguntasEncuesta = data;
    });
  }

  extraerOpciones(idPregunta: number): void {
    this.opciones = this.preguntasEncuesta[idPregunta].opciones;
    console.log(this.opciones);
  }

  // estos dos metodos no sé para qué son
  loadEncuesta(): void{
    let id = JSON.parse(localStorage.getItem('estudioID'));
    this.encuestaService.getEncuestaEstudio(id).subscribe(data => {
      this.encuestas = data;
    });
  }

  loadPreguntas(): void{
    let id = JSON.parse(localStorage.getItem('estudioID'));
    this.encuestaService.getEncuestaEstudio(id).subscribe(data => {
      this.encuestas = data;
    });
  }

}
