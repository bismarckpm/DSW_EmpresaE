import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormGroup, Validators } from '@angular/forms';
import { Encuesta } from 'src/app/models/encuesta';
import { EncuestaService } from '../../services/encuesta.service';

@Component({
  selector: 'app-lista-encuesta',
  templateUrl: './lista-encuesta.component.html',
  styleUrls: ['./lista-encuesta.component.css']
})
export class ListaEncuestaComponent implements OnInit {
  [x: string]: any;

  encuestas: any = [];
  formEncuesta: FormGroup;
  namePattern: any = /^[A-Za-z0-9\s]+$/;

  constructor( private servicio: EncuestaService ) {
    this.loadEncuestas();
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
