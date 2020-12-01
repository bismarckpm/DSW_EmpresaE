import { Component, OnInit, Input } from '@angular/core';
import { CategoriaService } from 'src/app/services/categoria.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-categoria',
  templateUrl: './form-categoria.component.html',
  styleUrls: ['./form-categoria.component.css']
})
export class FormCategoriaComponent implements OnInit {

  @Input() categoria ={ id:0,nombre:'',estatus:''}
  constructor(
    public categoriaService:CategoriaService,
    public router :Router
  ) { }

  ngOnInit(): void {
  }

  addCategoria(categoria){
    this.categoriaService.createCategoria(this.categoria).subscribe((data: {}) => {
    })
  }

}
