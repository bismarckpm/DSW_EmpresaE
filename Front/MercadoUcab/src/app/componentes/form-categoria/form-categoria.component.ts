import { Component, OnInit, Input } from '@angular/core';
import { CategoriaService } from 'src/app/services/categoria.service';
import { Router } from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-form-categoria',
  templateUrl: './form-categoria.component.html',
  styleUrls: ['./form-categoria.component.css']
})
export class FormCategoriaComponent implements OnInit {

  /// PAra validar
  formCategoria: FormGroup;
  namePattern: any = /^[A-Za-z0-9\s]+$/;

  @Input() categoria ={ _id:0,nombre:'',estado:''};

  constructor(
    public categoriaService: CategoriaService,
    public router: Router,
    private formBuilder: FormBuilder
  ) { this.createForm(); }

  ngOnInit(): void {
  }

  addCategoria(categoria){

    if (this.formCategoria.valid) {
      this.categoriaService.createCategoria(this.categoria).subscribe((data: {}) => {
      });
    }
    else{
      alert('FILL ALL FIELDS');
    }


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
