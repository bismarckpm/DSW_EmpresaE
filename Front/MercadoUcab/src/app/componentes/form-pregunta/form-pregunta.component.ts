import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { PreguntaService } from 'src/app/services/pregunta.service';

import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { TipoPreguntaService } from 'src/app/services/tipo-pregunta.service';

@Component({
  selector: 'app-form-pregunta',
  templateUrl: './form-pregunta.component.html',
  styleUrls: ['./form-pregunta.component.css']
})
export class FormPreguntaComponent implements OnInit {

  ///Declaracion de l obejto Pregunta 
  @Input() Pregunta ={ 
    _id:0, 
    descripcion:'',
    estado:'',
    dtoTipoPregunta:{
      _id:0,
      estado:'',
      tipo:''
    } ,
    dtoSubcategoria:{
      _id:0, 
      nombre:'',
      estado:'', 
      }
    };
    
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
      if (this.formPregunta.valid) {
        this.preguntaService.createPregunta(this.Pregunta).subscribe((data: {}) => {
        })
      }
      else{
        alert(' LLenar todos los campos por favor');
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

  createForm(){
    this.formPregunta = this.formBuilder.group({
      descripcionPregunta: ['', [Validators.required, Validators.pattern(this.patronDescripcionPregunta)]],
      estadoPregunta: ['', Validators.required],
      tipoPregunta: ['', Validators.required],
      nombreSubcategoria: ['', Validators.required],
    });
  }

}
