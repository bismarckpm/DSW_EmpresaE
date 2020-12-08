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
      _id: 1,
      estado: 'a',
      fechaInicio: '10/01/2000',
      fechaFin: null,
      dtoEstudio: null,
      dtoPregunta:{
        _id: 1,
        estado: 'a',
        descripcion: 'descripcion',
        dtoTipoPregunta:{
          _id:1,
          estado:'a',
          tipo:'simple',
        },
        dtoSubcategoria:{
          _id:1,
          nombre:'pepe',
          estado:'a',
          dtoCategoria:{
            _id:1,
            nombre:'hola',
            estado:'a'
          }
        },
      }
    },
    {
      _id:2 ,
      estado: 'a',
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
