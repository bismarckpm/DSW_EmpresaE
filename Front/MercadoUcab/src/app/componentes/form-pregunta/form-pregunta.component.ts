import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { Console } from 'console';
import { FORMERR } from 'dns';
import { stringify } from 'querystring';
import { Opcion } from 'src/app/models/opcion';
import { Tipo } from 'src/app/models/tipo';
import { TipoPregunta } from 'src/app/models/tipo-pregunta';
import { PreguntaService } from 'src/app/services/pregunta.service';

import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { TipoPreguntaService } from 'src/app/services/tipo-pregunta.service';

@Component({
  selector: 'app-form-pregunta',
  templateUrl: './form-pregunta.component.html',
  styleUrls: ['./form-pregunta.component.css']
})
export class FormPreguntaComponent implements OnInit {
  desplegarRango=false;
  desplegar=false;
  ///Declaracion de l obejto Pregunta
  @Input() Pregunta ={
    _id:0,
    descripcion:'',
    estado:'',
    tipo:{
      _id:0,
      estado:'',
      tipo:''
    } ,
    subcategoria:{
      _id:0,
      nombre:'',
      estado:'',
      },
      opciones:[]
    };

     Opciones: Opcion[]=[];


     ////variables para los drops
    tipopregunta:any;
    subcategoria:any;

  //Validaciones de  datos
  formPregunta: FormGroup;
  patronDescripcionPregunta: any = /^[A-Za-z0-9\s]+$/;

  constructor(
    public preguntaService:PreguntaService,
    public subcategoriaService: SubcategoriaService,
    public tipopreguntaService:TipoPreguntaService,
    public router:Router,
    private formBuilder: FormBuilder
    ) {this.createForm();}

    ngOnInit(): void {
      this.addsubcategoria();
      this.addtipopregunta();
    }


  addPregunta(){
  console.log(this.Opciones)

      if (this.formPregunta.valid) {

            if(this.Opciones.length > 0){
              console.log("if de opciones ")
                this.MeterOpciones()
            }

        this.preguntaService.createPregunta(this.Pregunta).subscribe((data: {}) => {
        })
      }
      else{
        alert(' LLenar todos los campos por favor');
      }
      location.reload();
  }

//////////////////////////// Metodo para las opciones //////////////////

MeterOpciones(){
  console.log("Entro en el meterOpciones");
let i
  ///Esto lo de abajo funciona solo con arreglo
  for(i=0;  i < this.Opciones.length ; i++){
    console.log("ejecuto el for ");
     this.Pregunta.opciones[i]=this.Opciones[i]
     console.log(this.Pregunta.opciones);
 }
}


ValidarTipopregunta(tipoid){

  console.log("entre en validar ")


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







  /////Para los dropdown////////////////////////////////////////////
  addsubcategoria(){
    this.subcategoriaService.getsubcategorias().subscribe((Subcategoria: {}) => {
      this.subcategoria= Subcategoria;
    })

  }
  addtipopregunta(){
    this.tipopreguntaService.getTipoPreguntas().subscribe((TipoPregunta: {}) => {
      this.tipopregunta= TipoPregunta;
    })

  }
/////////////////////////////////////////////////////////////////////////

  //Validaciones de Pregunta
  get descripcionPregunta(){return this.formPregunta.get('descripcionPregunta');}
  get estadoPregunta(){ return this.formPregunta.get('estadoPregunta');}
  get tipoPregunta(){return this.formPregunta.get('tipoPregunta');}
  get nombreSubcategoria(){return this.formPregunta.get('nombreSubcategoria');}
  get opciocPregunta(){return this.formPregunta.get('opciocPregunta');}
  get opciocPreguntaRango(){return this.formPregunta.get('opciocPreguntaRango');}

  createForm(){
    this.formPregunta = this.formBuilder.group({
      descripcionPregunta: ['', Validators.required],
      estadoPregunta: ['', Validators.required],
      tipoPregunta: ['', Validators.required],
      nombreSubcategoria: ['', Validators.required],
      opciocPregunta: ['', Validators.pattern(this.patronDescripcionPregunta)],
      opciocPreguntaRango: ['', Validators.pattern(this.patronDescripcionPregunta)],
    });
  }

}
