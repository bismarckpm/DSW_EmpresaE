import {Component, Input, OnInit} from '@angular/core';
import {Estudio} from '../../../models/estudio';
import {EstudioService} from '../../../services/estudio.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Encuestado} from '../../../models/encuestado';

@Component({
  selector: 'app-lista-estudios-analista',
  templateUrl: './lista-estudios-analista.component.html',
  styleUrls: ['./lista-estudios-analista.component.css']
})
export class ListaEstudiosAnalistaComponent implements OnInit {

  // Declaracion de variables
  estudios: Estudio[] = [];
  encuestados: Encuestado[] = [];
  _id = this.actRoute.snapshot.params._id;
  @Input() analistaData = {_id: 0, comentarioAnalista: '', estado: ''};
  formEstudioAnalista: FormGroup;


  constructor(
    public estudioService: EstudioService,
    private formBuilder: FormBuilder,
    public actRoute: ActivatedRoute,
    public router: Router

  ) { this.createForm(); }

  ngOnInit(): void {
    this.loadEstudios();
  }

  editar(estudio){
    this.analistaData = estudio;
  }

  loadEstudios(): void {
    const user = JSON.parse(localStorage.getItem('usuarioID'));
    this.estudioService.getEstudioAnalista(user).subscribe(data => {
      this.estudios = data;
    });
  }

  loadDataMuestra(id): void {
    this.estudioService.getDataMuestra(id).subscribe(data => {
      this.encuestados = data;
    });
  }

  updateEstudio(){
    this.estudioService.updateEstudio(this.analistaData._id, this.analistaData).subscribe(data => {
    });
    this.loadEstudios();
  }

  get comentarioAnalistaEstudio(){ return this.formEstudioAnalista.get('comentarioAnalistaEstudio'); }
  get estadoEstudioAnalista(){ return this.formEstudioAnalista.get('estadoEstudioAnalista'); }

  createForm() {
    this.formEstudioAnalista = this.formBuilder.group({
      comentarioAnalistaEstudio: ['', [Validators.required, Validators.maxLength(100)]],
      estadoEstudioAnalista: ['', Validators.required],
    });
  }
}
