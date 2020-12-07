import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-form-encuesta',
  templateUrl: './form-encuesta.component.html',
  styleUrls: ['./form-encuesta.component.css']
})
export class FormEncuestaComponent implements OnInit {

  cantPreguntas = 1;

  constructor() { }

  ngOnInit(): void {
  }

  agregarEncuesta(): void{
    console.log('agrego encuesta');
  }

}
