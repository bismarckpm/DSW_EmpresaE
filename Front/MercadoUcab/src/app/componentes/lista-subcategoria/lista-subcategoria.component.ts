import { Component, OnInit, Input } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { CategoriaService } from 'src/app/services/categoria.service';



@Component({
  selector: 'app-lista-subcategoria',
  templateUrl: './lista-subcategoria.component.html',
  styleUrls: ['./lista-subcategoria.component.css']
})
export class ListaSubcategoriaComponent implements OnInit {

  Subcategoria: any=[];
  id = this.actRoute.snapshot.params['id'];

  @Input()subcategoriaData: any={};
  
  categoria:any;


  constructor(
    public subcategoriaService: SubcategoriaService,
    public categoriaService :CategoriaService,
    public actRoute: ActivatedRoute,
    public router: Router
  ) { }

  ngOnInit(): void {
    this.loadSubcategoria();
   // this.addcategoria();

  }

  loadSubcategoria(){
    return this.subcategoriaService.getsubcategorias().subscribe((data: {}) => {
      this.Subcategoria = data;
    })
  }

  deleteSubcategoria(id) {

      this.subcategoriaService.deletesubcategoria(id).subscribe(data => {
        this.loadSubcategoria()
      })
   
  }
  
  updateSubcategoria(){
    console.log('Funciona');
    
     /* this.subcategoriaService.updatesubcategoria(this.subcategoriaData.id, this.subcategoriaData).subscribe(data => {
      })*/
    
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
