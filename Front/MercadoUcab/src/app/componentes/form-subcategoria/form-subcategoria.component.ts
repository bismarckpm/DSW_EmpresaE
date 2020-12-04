import { Component, OnInit, Input } from '@angular/core';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { Router } from '@angular/router';
import { CategoriaService } from 'src/app/services/categoria.service';
import {Categoria} from 'src/app/models/categoria'


@Component({
  selector: 'app-form-subcategoria',
  templateUrl: './form-subcategoria.component.html',
  styleUrls: ['./form-subcategoria.component.css']
})
export class FormSubcategoriaComponent implements OnInit {
  
  @Input() subcategoria ={ id:0, nombre:'',estatus:'', dtoCategoria:{id:0}};
  categoria:any;

  constructor( 
    public subcategoriaService: SubcategoriaService,
    public categoriaService:CategoriaService,
    public router:Router
  ) { }

  ngOnInit(): void {
    this.addcategoria();

  }


  addSubcategoria(subcategoria){
    this.subcategoriaService.createsubcategoria(this.subcategoria).subscribe((data: {}) => {
    })
  }

  

    ///Esto es para mostrar en los drops doww
    addcategoria(){
      this.categoriaService.getCategorias().subscribe((Categorias: {}) => {
        this.categoria= Categorias;
      })
    }

}
