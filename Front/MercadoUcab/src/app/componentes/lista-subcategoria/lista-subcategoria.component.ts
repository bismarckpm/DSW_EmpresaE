import { Component, OnInit, Input } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { CategoriaService } from 'src/app/services/categoria.service';
import { Subcategoria } from 'src/app/models/subcategoria';




@Component({
  selector: 'app-lista-subcategoria',
  templateUrl: './lista-subcategoria.component.html',
  styleUrls: ['./lista-subcategoria.component.css']
})
export class ListaSubcategoriaComponent implements OnInit {

  Subcategoria: Subcategoria[]=[];
  
  id = this.actRoute.snapshot.params['id'];

  @Input()subcategoriaData={ id:0, nombre:'',estatus:'', dtoCategoria:{id:0}};
  
  categoria:any;


  constructor(
    public subcategoriaService: SubcategoriaService,
    public categoriaService :CategoriaService,
    public actRoute: ActivatedRoute,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.loadSubcategoria();

  }

  loadSubcategoria():void {
    this.subcategoriaService.getsubcategorias().subscribe(data => {
      this.Subcategoria = data;
    })
  }

  deleteSubcategoria(id) {

      this.subcategoriaService.deletesubcategoria(id).subscribe(data => {
        this.loadSubcategoria()
      })
   
  }
  
  updateSubcategoria(){
     this.subcategoriaService.updatesubcategoria(this.subcategoriaData.id, this.subcategoriaData).subscribe(data => {
      })
    
  }

  editar(subcategoria){  
    this.addcategoria();
    this.subcategoriaData= subcategoria;
  }
  
  ///Esto es para mostrar en los drops doww
  addcategoria(){
    this.categoriaService.getCategorias().subscribe((Categorias: {}) => {
      this.categoria= Categorias;
    })
  }





}
