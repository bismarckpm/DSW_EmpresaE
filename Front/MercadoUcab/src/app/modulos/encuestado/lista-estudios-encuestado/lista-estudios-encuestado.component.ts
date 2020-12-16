import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {EstudioService} from '../../../services/estudio.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Estudio} from '../../../models/estudio';

@Component({
  selector: 'app-lista-estudios-encuestado',
  templateUrl: './lista-estudios-encuestado.component.html',
  styleUrls: ['./lista-estudios-encuestado.component.css']
})
export class ListaEstudiosEncuestadoComponent implements OnInit {

  estudios: Estudio[] = [];
  _id = this.actRoute.snapshot.params._id;
  @Input() encuestadoData = {};
  formEstudioEncuestado: FormGroup;

  constructor(
    public estudioService: EstudioService,
    private formBuilder: FormBuilder,
    public actRoute: ActivatedRoute,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.loadEstudios();
  }

  loadEstudios(): void{
    const id = JSON.parse(localStorage.getItem('usuarioID'));
    this.estudioService.getEstudioEncuestado(id).subscribe(data => {
      this.estudios = data;
    });
  }

}
