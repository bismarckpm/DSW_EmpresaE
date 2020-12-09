import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { TipoPreguntaService } from 'src/app/services/tipo-pregunta.service';
import { Pregunta } from '../../models/pregunta';
import { PreguntaService } from '../../services/pregunta.service';

@Component({
  selector: 'app-lista-preguntas',
  templateUrl: './lista-preguntas.component.html',
  styleUrls: ['./lista-preguntas.component.css']
})
export class ListaPreguntasComponent implements OnInit {

  preguntas: Pregunta[] = [];
  _id = this.actRoute.snapshot.params['_id'];
  @Input() preguntaData ={ _id:0, descripcion:'',estado:'',tipo:{_id:0,estado:'',tipo:''} ,subcategoria:{_id:0, nombre:'',estado:'' }};
   subcategorias:any;
   tipopreguntas:any;

   formPregunta: FormGroup;
   namePattern: any = /^[A-Za-z0-9\s]+$/;

  constructor(
    public preguntaService:PreguntaService,
    public subcategoriaService:SubcategoriaService,
    public tipopreguntaService:TipoPreguntaService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder

  ) { this.createForm();}


  ngOnInit(): void {
    this.loadpregunta();

  }

  loadpregunta():void {
    this.preguntaService.getPreguntas().subscribe(data => {
      this.preguntas = data;
    })
  }

  deletePregunta(id) {

      this.preguntaService.deletePregunta(id).subscribe(data => {
        this.loadpregunta()
      })

  }

  updatePregunta(){
     this.preguntaService.updatePregunta(this.preguntaData._id, this.preguntaData).subscribe(data => {
      })
      this.loadpregunta();

  }

  editar(pregunta){
    this.addSubcategoria();
    this.addTipoPregunta()
    this.preguntaData= pregunta;
  }

  ///Esto es para mostrar en los drops doww
  addSubcategoria(){
    this.subcategoriaService.getsubcategorias().subscribe((data: {}) => {
      this.subcategorias= data;
    })
  }
  addTipoPregunta(){
    this.tipopreguntaService.getTipoPreguntas().subscribe((data: {}) => {
      this.tipopreguntas= data;
    })
  }

  //Validaciones de Pregunta
  get descripcionPregunta(){return this.formPregunta.get('descripcionPregunta');}
  get estadoPregunta(){ return this.formPregunta.get('estadoPregunta');}
  get tipoPregunta(){return this.formPregunta.get('tipoPregunta');}
  get nombreSubcategoria(){return this.formPregunta.get('nombreSubcategoria');}

  createForm(){
    this.formPregunta = this.formBuilder.group({
      descripcionPregunta: ['', [Validators.required, Validators.pattern(this.namePattern)]],
      estadoPregunta: ['', Validators.required],
      tipoPregunta: ['', Validators.required],
      nombreSubcategoria: ['', Validators.required],
    });
  }



}
