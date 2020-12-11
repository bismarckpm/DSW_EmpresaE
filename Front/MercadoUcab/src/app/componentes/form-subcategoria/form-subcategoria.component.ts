import { Component, OnInit, Input } from '@angular/core';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { Router } from '@angular/router';
import { CategoriaService } from 'src/app/services/categoria.service';
import {Categoria} from 'src/app/models/categoria';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-form-subcategoria',
  templateUrl: './form-subcategoria.component.html',
  styleUrls: ['./form-subcategoria.component.css']
})
export class FormSubcategoriaComponent implements OnInit {

  @Input() subcategoria ={ _id:0, nombre:'',estado:'', categoria:{_id:0}};
  categoria:any;
  // Validacion de datos
  formSubcategoria: FormGroup;
  namePattern: any = /^[A-Za-z0-9\s]+$/;

  constructor(
    public subcategoriaService: SubcategoriaService,
    public categoriaService:CategoriaService,
    public router:Router,
    private formBuilder: FormBuilder
  ) {  this.createForm();}

  ngOnInit(): void {
    this.addcategoria();

  }


  addSubcategoria(subcategoria){
    if (this.formSubcategoria.valid) {
    this.subcategoriaService.createsubcategoria(this.subcategoria).subscribe((data: {}) => {
    })
  }
  else{
    alert(' LLenar todos los campos por favor');
  }
  }



    ///Esto es para mostrar en los drops doww
    addcategoria(){

      this.categoriaService.getCategorias().subscribe((Categorias: {}) => {
        this.categoria= Categorias;
      })

    }


   /// Validacion de Datos
    get nombreSubcategoria(){
      return this.formSubcategoria.get('nombreSubcategoria');
    }

    get estadoSubcategoria(){
      return this.formSubcategoria.get('estadoSubcategoria');
    }

    get CATEGORIA(){
      return this.formSubcategoria.get('CATEGORIA');
    }

    createForm(){
      this.formSubcategoria = this.formBuilder.group({
        nombreSubcategoria: ['', [Validators.pattern(this.namePattern), Validators.required]],
        estadoSubcategoria: ['', Validators.required],
        CATEGORIA: ['', Validators.required],
      });
    }

}
