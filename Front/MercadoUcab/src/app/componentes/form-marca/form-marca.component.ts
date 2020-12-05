import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from "@angular/router";
import {MarcaService} from "../../services/marca.service";

@Component({
  selector: 'app-form-marca',
  templateUrl: './form-marca.component.html',
  styleUrls: ['./form-marca.component.css']
})
export class FormMarcaComponent implements OnInit {

  @Input() marca = { id: 0, nombre: '', estado: ''};

  formMarca: FormGroup;
  patronNombreMarca: any = /^[A-Za-z0-9\s]+$/;

  get nombreMarca(){
    return this.formMarca.get('nombreMarca');
  }

  get estadoMarca(){
    return this.formMarca.get('estadoMarca');
  }

  constructor(
    public marcaService: MarcaService,
    public router: Router,
    private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm(){
    this.formMarca = this.formBuilder.group({
      nombreMarca: ['', [Validators.pattern(this.patronNombreMarca), Validators.required]],
      estadoMarca: ['', Validators.required],
    });
  }

  ngOnInit(): void {
  }

  addMarca(marca){
    if (this.formMarca.valid) {
      this.marcaService.createMarca(this.marca).subscribe((data: {}) => {
      });
    }
    else{
      alert(' LLenar todos los campos por favor');
    }
  }

  agregarMarca() {
    console.log('agregó marca');
  }
}
