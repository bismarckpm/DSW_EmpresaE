import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Opcion } from 'src/app/models/opcion';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { TipoPreguntaService } from 'src/app/services/tipo-pregunta.service';
import { Pregunta } from '../../models/pregunta';
import { PreguntaService } from '../../services/pregunta.service';
import {ToastService} from "../../services/toast.service";

@Component({
  selector: 'app-lista-preguntas',
  templateUrl: './lista-preguntas.component.html',
  styleUrls: ['./lista-preguntas.component.css']
})
export class ListaPreguntasComponent implements OnInit {
  desplegarRango=false;
  desplegar=false;
  preguntas: Pregunta[] = [];
  Opciones: Opcion[]=[];

  _id = this.actRoute.snapshot.params['_id'];
  @Input() preguntaData ={ _id:0, descripcion:'',estado:'',tipo:{_id:0,estado:'',tipo:''} ,subcategoria:{_id:0, nombre:'',estado:'',}, opciones:[] };
   subcategoria:any;
   tipopregunta:any;
   aux: any = [];

   aux: any;
   formPregunta: FormGroup;
   namePattern: any = /^[A-Za-z0-9\s]+$/;

  constructor(
    public preguntaService:PreguntaService,
    public subcategoriaService:SubcategoriaService,
    public tipopreguntaService:TipoPreguntaService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private toast: ToastService,
    private formBuilder: FormBuilder

  ) { this.createForm();}


  ngOnInit(): void {
    this.loadpregunta();

  }


  //////////////////////////// Metodo para las opciones //////////////////

MeterOpciones(){
  console.log("Entro en el meterOpciones");
  let i;
  for(i=0;  i < this.Opciones.length ; i++){
    console.log("ejecuto el for ");
     this.preguntaData.opciones[i]=this.Opciones[i]
     console.log(this.preguntaData);
 }
}

ValidarTipopregunta(tipoid){
  console.log("entre en validar ")
  if(tipoid>0){
      if( tipoid == 3|| tipoid==4){
        this.desplegar= true;
        this.desplegarRango= false;
        this.Opciones.length=0;
      }else if( tipoid==5){
        this.desplegar= false;
        this.desplegarRango= true;
        this.Opciones.length=0;
      } else{
        this.desplegarRango= false;
        this.desplegar= false;
        this.Opciones.length=0;
      }
  }
}



  loadpregunta():void {
    this.preguntaService.getPreguntas().subscribe(data => {
      this.aux = data;
      this.HandleErrorGet();
    })
  }

  deletePregunta(id) {

      this.preguntaService.deletePregunta(id).subscribe(data => {
        this.loadpregunta()
      })
    location.reload();

  }

  updatePregunta(){

    this.MeterOpciones()

    console.log(this.preguntaData);

    this.preguntaService.updatePregunta(this.preguntaData._id, this.preguntaData).subscribe(data => {
      })

  }

  editar(pregunta){
    this.addSubcategoria();
    this.addTipoPregunta()
    this.preguntaData= pregunta;
  }

  ///Esto es para mostrar en los drops doww
  addSubcategoria(){
    this.subcategoriaService.getsubcategorias().subscribe((data: {}) => {
      this.subcategoria= data;
    })
  }
  addTipoPregunta(){
    this.tipopreguntaService.getTipoPreguntas().subscribe((data: {}) => {
      this.tipopregunta= data;
    })
  }

  HandleErrorGet(){
    if (this.aux.estado == 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
      this.preguntas = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }

  HandleErrorPostPut(){
    if (this.aux.estado == 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }

  //Validaciones de Pregunta
  get descripcionPregunta(){return this.formPregunta.get('descripcionPregunta');}
  get estadoPregunta(){ return this.formPregunta.get('estadoPregunta');}
  get tipoPregunta(){return this.formPregunta.get('tipoPregunta');}
  get nombreSubcategoria(){return this.formPregunta.get('nombreSubcategoria');}
  get opcionPregunta1(){return this.formPregunta.get('opcionPregunta1');}
  get opcionPregunta2(){return this.formPregunta.get('opcionPregunta2');}
  get opcionPregunta3(){return this.formPregunta.get('opcionPregunta3');}
  get opcionPregunta4(){return this.formPregunta.get('opcionPregunta4');}
  get opciocPreguntaRango(){return this.formPregunta.get('opciocPreguntaRango');}

  createForm(){
    this.formPregunta = this.formBuilder.group({
      descripcionPregunta: ['', Validators.required],
      estadoPregunta: ['', Validators.required],
      tipoPregunta: ['', Validators.required],
      nombreSubcategoria: ['', Validators.required],
      opcionPregunta1: ['', Validators.pattern(this.namePattern)],
      opcionPregunta2: ['', Validators.pattern(this.namePattern)],
      opcionPregunta3: ['', Validators.pattern(this.namePattern)],
      opcionPregunta4: ['', Validators.pattern(this.namePattern)],
      opciocPreguntaRango: ['', Validators.pattern(this.namePattern)],
    });
  }

  HandleErrorGet(): void {
    if (this.aux.estado === 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
      this.preguntas = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }

}
