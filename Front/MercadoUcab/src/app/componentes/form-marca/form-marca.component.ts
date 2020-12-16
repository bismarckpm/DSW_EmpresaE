import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MarcaService} from '../../services/marca.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-form-marca',
  templateUrl: './form-marca.component.html',
  styleUrls: ['./form-marca.component.css']
})
export class FormMarcaComponent implements OnInit {

  @Input() marca = { _id: 0, nombre: '', estado: ''};
  formMarca: FormGroup;
  patronNombreMarca: any = /^[A-Za-z0-9\s]+$/;

  constructor(
    private formBuilder: FormBuilder,
    public marcaService: MarcaService,
    public router: Router) {
    this.createForm();
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

  get nombreMarca(){
    return this.formMarca.get('nombreMarca');
  }

  get estadoMarca(){
    return this.formMarca.get('estadoMarca');
  }

  createForm(){
    this.formMarca = this.formBuilder.group({
      nombreMarca: ['', [Validators.pattern(this.patronNombreMarca), Validators.required]],
      estadoMarca: ['', Validators.required],
    });
  }

  agregarMarca(){
    console.log('agregó marca');
  }
}
