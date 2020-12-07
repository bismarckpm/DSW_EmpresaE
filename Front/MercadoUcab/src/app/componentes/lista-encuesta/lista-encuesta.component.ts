import { Component, OnInit } from '@angular/core';
import { Encuesta } from 'src/app/models/encuesta';

@Component({
  selector: 'app-lista-encuesta',
  templateUrl: './lista-encuesta.component.html',
  styleUrls: ['./lista-encuesta.component.css']
})
export class ListaEncuestaComponent implements OnInit {

  encuestas: Encuesta[] = [
    {
      id: 1,
      estado: 'A',
      fechaInicio: '10/01/2000',
      fechaFin: null,
      dtoEstudio: null,
      dtoPregunta: {
        id: 1,
        estado: 'A',
        descripcion: 'descripcion'
      },
    },
    {
      id: 1,
      estado: 'A',
      fechaInicio: '10/01/2000',
      fechaFin: null,
      dtoEstudio: null,
      dtoPregunta: null,
    },
  ];

  constructor() { }

  ngOnInit(): void {
  }


  actualizarEncuesta(): void {
    console.log('actualizo encuesta');
  }

  eliminarEncuesta(encuesta: Encuesta): void {
    console.log('elimino encuesta');
  }

}
