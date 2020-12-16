import {Component, Input, OnInit} from '@angular/core';
import {Estudio} from '../../../models/estudio';
import {EstudioService} from '../../../services/estudio.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-lista-estudios-analista',
  templateUrl: './lista-estudios-analista.component.html',
  styleUrls: ['./lista-estudios-analista.component.css']
})
export class ListaEstudiosAnalistaComponent implements OnInit {

  // Declaracion de variables
  estudios: Estudio[] = [];
  _id = this.actRoute.snapshot.params['_id'];
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
    let user = JSON.parse(localStorage.getItem('usuarioID'));
    this.estudioService.getEstudioAnalista(user).subscribe(data => {
      this.estudios = data;
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
      comentarioAnalista: ['', [Validators.required, Validators.maxLength(100)]],
      estadoEstudio: ['', Validators.required],
    });
  }
}
