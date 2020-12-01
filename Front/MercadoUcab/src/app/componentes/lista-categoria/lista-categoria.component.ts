import { Component, OnInit, Input } from '@angular/core';
import { CategoriaService } from 'src/app/services/categoria.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-lista-categoria',
  templateUrl: './lista-categoria.component.html',
  styleUrls: ['./lista-categoria.component.css']
})
export class ListaCategoriaComponent implements OnInit {

  Categoria: any=[];
  id = this.actRoute.snapshot.params['id'];

  @Input() categoriaData: any = {};
  constructor( 
    public categoriaService:CategoriaService,
    public actRoute: ActivatedRoute,
    public router: Router
    ) { }

  ngOnInit(): void {
    this.loadCategorias()
  }

  loadCategorias(){
    return this.categoriaService.getCategorias().subscribe((data: {}) => {
      this.Categoria = data;
    })
  }

  // Delete Categoria
  deleteCategoria(id) {
    if (window.confirm('Are you sure, you want to delete?')){
      this.categoriaService.deleteCategoria(id).subscribe(data => {
        this.loadCategorias()
      })
    }
  }
  
  updateCategoria() {
 
      this.categoriaService.updateCategoria(this.categoriaData.id, this.categoriaData).subscribe(data => {
        
      })
    
  }

  editar(categoria){
    this.categoriaData= categoria;
  }

}
