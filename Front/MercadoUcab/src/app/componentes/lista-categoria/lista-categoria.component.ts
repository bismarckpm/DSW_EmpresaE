import { Component, OnInit, Input } from '@angular/core';
import { CategoriaService } from 'src/app/services/categoria.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-lista-categoria',
  templateUrl: './lista-categoria.component.html',
  styleUrls: ['./lista-categoria.component.css']
})
export class ListaCategoriaComponent implements OnInit {

   /// PAra validar
   formCategoria: FormGroup;
   namePattern: any = /^[A-Za-z0-9\s]+$/;

  Categoria: any = [];
  id = this.actRoute.snapshot.params.id;

  @Input() categoriaData: any = {};
  constructor(
    public categoriaService: CategoriaService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder
    ) {this.createForm(); }

  ngOnInit(): void {
    this.loadCategorias();
  }

  loadCategorias(){
    return this.categoriaService.getCategorias().subscribe((data: {}) => {
      this.Categoria = data;
    });
  }

  // Delete Categoria
  deleteCategoria(id) {
    if (window.confirm('Are you sure, you want to delete?')){
      this.categoriaService.deleteCategoria(id).subscribe(data => {
        this.loadCategorias();
      });
    }
  }

  updateCategoria() {
    if (this.formCategoria.valid) {
      this.categoriaService.updateCategoria(this.categoriaData.id, this.categoriaData).subscribe(data => {
      });
    }
    else{
      alert('FILL ALL FIELDS');
    }
  }
  // Sustitucion de variables para el update
  editar(categoria){
    this.categoriaData = categoria;
  }

   ///// Metodos para las validaciones
   get nombreCategoria(){
    return this.formCategoria.get('nombreCategoria');
  }

  get estadoCategoria(){
    return this.formCategoria.get('estadoCategoria');
  }

  createForm(){
    this.formCategoria = this.formBuilder.group({
      nombreCategoria: ['', [Validators.pattern(this.namePattern), Validators.required]],
      estadoCategoria: ['', Validators.required],
    });
  }

}
