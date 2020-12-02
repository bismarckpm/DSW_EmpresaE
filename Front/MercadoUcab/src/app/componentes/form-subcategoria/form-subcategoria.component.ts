import { Component, OnInit, Input } from '@angular/core';
import { SubsubcategoriaService } from 'src/app/services/subcategoria.service';
import { Router } from '@angular/router';
import { CategoriaService } from 'src/app/services/categoria.service';

@Component({
  selector: 'app-form-subcategoria',
  templateUrl: './form-subcategoria.component.html',
  styleUrls: ['./form-subcategoria.component.css']
})
export class FormSubcategoriaComponent implements OnInit {
  
  @Input() subcategoria ={ id:0,nombre:'',estatus:'',id_categoria:0}

  constructor( 
    public subcategoriaService: SubsubcategoriaService,
    public categoriaService:CategoriaService,
    public router:Router
  ) { }

  ngOnInit(): void {
  }

  addSubcategoria(subcategoria){
    this.subcategoriaService.createsubcategoria(this.subcategoria).subscribe((data: {}) => {

    })
  }

}
