import { Component, OnInit, Input } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { CategoriaService } from 'src/app/services/categoria.service';
import { Subcategoria } from 'src/app/models/subcategoria';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {ToastService} from "../../services/toast.service";




@Component({
  selector: 'app-lista-subcategoria',
  templateUrl: './lista-subcategoria.component.html',
  styleUrls: ['./lista-subcategoria.component.css']
})
export class ListaSubcategoriaComponent implements OnInit {

    // Declaracion de las varibales del componente que se usa
  Subcategoria: Subcategoria[] = [];
  aux: any = [];
  _id = this.actRoute.snapshot.params._id;
  @Input()subcategoriaData = { _id: 0, nombre: '', estado: '', categoria: {_id: 0}};
  categoria: any;

  // Declaracion para las Validaciones
  formSubcategoria: FormGroup;
  namePattern: any = /^[A-Za-z0-9\s]+$/;


  constructor(
    public subcategoriaService: SubcategoriaService,
    public categoriaService: CategoriaService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private toast: ToastService,
    private formBuilder: FormBuilder
  ) {  this.createForm(); }

  ngOnInit(): void {
    this.loadSubcategoria();

  }

  loadSubcategoria(): void {
    this.subcategoriaService.getsubcategorias().subscribe(data => {
      this.aux = data;
      this.HandleErrorGet();
    });
  }

  deleteSubcategoria(id) {

      this.subcategoriaService.deletesubcategoria(id).subscribe(data => {
        this.loadSubcategoria();
      });
      location.reload();
  }

  updateSubcategoria(){
     this.subcategoriaService.updatesubcategoria(this.subcategoriaData._id, this.subcategoriaData).subscribe(data => {
      });
     location.reload();
  }

  editar(subcategoria){
    this.addcategoria();
    this.subcategoriaData = subcategoria;
  }

  /// Esto es para mostrar en los drops doww
  addcategoria(){
    this.categoriaService.getCategorias().subscribe((Categorias: {}) => {
      this.categoria = Categorias;
    });
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

  HandleErrorGet(): void {
    if (this.aux.estado === 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
      this.Subcategoria = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }



}
