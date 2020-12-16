import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {EstudioService} from '../../../services/estudio.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Estudio} from '../../../models/estudio';
import {EncuestaService} from '../../../services/encuesta.service';
import {Encuesta} from '../../../models/encuesta';

@Component({
  selector: 'app-lista-estudios-encuestado',
  templateUrl: './lista-estudios-encuestado.component.html',
  styleUrls: ['./lista-estudios-encuestado.component.css']
})
export class ListaEstudiosEncuestadoComponent implements OnInit {

  estudios: Estudio[] = [];
  encuestas: Encuesta[] = [];
  _id = this.actRoute.snapshot.params._id;
  @Input() encuestadoData = {};
  formEstudioEncuestado: FormGroup;

  constructor(
    public estudioService: EstudioService,
    public encuestaService: EncuestaService,
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

  loadEncuesta(): void{
    let id = JSON.parse(localStorage.getItem('estudioID'));
    this.encuestaService.getEncuestaEstudio(id).subscribe(data => {
      this.encuestas = data;
    });
  }

}
