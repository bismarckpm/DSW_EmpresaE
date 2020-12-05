import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-form-clientes',
  templateUrl: './form-clientes.component.html',
  styleUrls: ['./form-clientes.component.css']
})
export class FormClientesComponent implements OnInit {

  formCliente: FormGroup;
  patronRIFCliente: any = /^[0-9]+$/;

  get rifCliente() {
    return this.formCliente.get('rifCliente');
  }

  get razonSocialCliente() {
    return this.formCliente.get('razonSocialCliente');
  }

  get estadoCliente() {
    return this.formCliente.get('estadoCliente');
  }

  constructor(private formBuilder: FormBuilder) {
    this.createForm();
  }

  createForm() {
    this.formCliente = this.formBuilder.group({
      rifCliente: ['', [Validators.required, Validators.pattern(this.patronRIFCliente)]],
      razonSocialCliente: ['', Validators.required],
      estadoCliente: ['', Validators.required],
    });
  }

  ngOnInit(): void {
  }


  /*agregarCliente() {
    console.log(this.item);
  }*/

  onSubmit() {
    if (this.formCliente.valid) {
      console.log(this.formCliente.value);
    } else {
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
  }

  /* agregarCliente(): void {
     console.log('agrego: ' + this.item.nombre);
     this.item.nombre = '';

   }*/
}
