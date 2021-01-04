import {Component, Input, OnInit} from '@angular/core';
import {Estudio} from '../../../models/estudio';
import {EstudioService} from '../../../services/estudio.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Encuestado} from '../../../models/encuestado';
import * as Highcharts from 'highcharts';
import highcharts3D from 'highcharts/highcharts-3d.src';
highcharts3D( Highcharts );


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

  infoGraficos: any = [];
  chartOptions: Highcharts.Options[] = [];
  highcharts = Highcharts;
  
 

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

  loadInfoGraficos(estudio): void {
    /*this.estudioService.getDataGraficos(estudio).subscribe(data => {
      this.infoGraficos = data;
       this.agregarDatos();
      console.log(this.infoGraficos);
    });*/
    this.infoGraficos= [
      {
          "_id": 58,
          "descripcion": "PregAbInt",
          "opcionesResultado": [],
          "tipo": {
              "_id": 1,
              "estado": "a",
              "tipo": "Comp"
          }
      },
      {
          "_id": 59,
          "descripcion": "PregVFInt",
          "opcionesResultado": [
              {
                  "_id": 76,
                  "descripcion": "Verdadero",
                  "estado": "a",
                  "valor": 2
              },
              {
                  "_id": 77,
                  "descripcion": "Falso",
                  "estado": "a",
                  "valor": 0
              }
          ],
          "tipo": {
              "_id": 2,
              "estado": "a",
              "tipo": "VyF"
          }
      },
      {
          "_id": 60,
          "descripcion": "PregSSInt",
          "opcionesResultado": [
              {
                  "_id": 78,
                  "descripcion": "p1",
                  "estado": "a",
                  "valor": 0
              },
              {
                  "_id": 79,
                  "descripcion": "p2",
                  "estado": "a",
                  "valor": 1
              },
              {
                  "_id": 80,
                  "descripcion": "p3",
                  "estado": "a",
                  "valor": 1
              },
              {
                  "_id": 81,
                  "descripcion": "p4",
                  "estado": "a",
                  "valor": 0
              }
          ],
          "tipo": {
              "_id": 3,
              "estado": "a",
              "tipo": "SS"
          }
      },
      {
          "_id": 61,
          "descripcion": "PregSMInt",
          "opcionesResultado": [
              {
                  "_id": 82,
                  "descripcion": "op1",
                  "estado": "a",
                  "valor": 1
              },
              {
                  "_id": 83,
                  "descripcion": "op2",
                  "estado": "a",
                  "valor": 0
              },
              {
                  "_id": 84,
                  "descripcion": "op3",
                  "estado": "a",
                  "valor": 2
              },
              {
                  "_id": 85,
                  "descripcion": "op4",
                  "estado": "a",
                  "valor": 1
              }
          ],
          "tipo": {
              "_id": 4,
              "estado": "a",
              "tipo": "SM"
          }
      },
      {
          "_id": 62,
          "descripcion": "PregVFDos",
          "opcionesResultado": [
              {
                  "_id": 86,
                  "descripcion": "Verdadero",
                  "estado": "a",
                  "valor": 1
              },
              {
                  "_id": 87,
                  "descripcion": "Falso",
                  "estado": "a",
                  "valor": 1
              }
          ],
          "tipo": {
              "_id": 2,
              "estado": "a",
              "tipo": "VyF"
          }
      }
  ]
  this.agregarDatos();
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
    let chartOptions: Highcharts.Options = {
      chart: {
        type: "pie",
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
        headerFormat: "",
        pointFormat:
          "<span style='color:{point.color}'>\u25CF</span> {point.name}: <b>{point.y}</b>",
        style: {
          fontSize: "10px"
        }
      },
      plotOptions: {
        pie: {
          allowPointSelect: true,
          cursor: "pointer",
          depth: 35,
          shadow: true,
          innerSize: "20%",
          dataLabels: {
            enabled: false
          },
          showInLegend: true
        }
      },
      series: [
        {
          type: "pie",
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
    });
  }
}
