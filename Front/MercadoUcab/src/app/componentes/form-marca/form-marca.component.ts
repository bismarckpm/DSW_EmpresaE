import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-form-marca',
  templateUrl: './form-marca.component.html',
  styleUrls: ['./form-marca.component.css']
})
export class FormMarcaComponent implements OnInit {

  formMarca: FormGroup;
  patronNombreMarca: any = /^[A-Za-z0-9\s]+$/;

  get nombreMarca(){
    return this.formMarca.get('nombreMarca');
  }

  get estadoMarca(){
    return this.formMarca.get('estadoMarca');
  }

  constructor(private formBuilder: FormBuilder) {
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

  agregarMarca(){
    console.log('agregó marca');
  }

  onSubmit() {
    if (this.formMarca.valid) {
      console.log(this.formMarca.value);
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
  }
}
