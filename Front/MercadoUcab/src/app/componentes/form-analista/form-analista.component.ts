import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AnalistaService} from '../../services/analista.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-form-analista',
  templateUrl: './form-analista.component.html',
  styleUrls: ['./form-analista.component.css']
})
export class FormAnalistaComponent implements OnInit {

  @Input() analista = {_id: 0, username: '', clave: '', correoElectronico: '', estado: '', rol: ''};
  formAnalista: FormGroup;
  patronCorreoAnalista: any = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

  constructor(
    private formBuilder: FormBuilder,
    public analistaService: AnalistaService,
    public router: Router) { this.createForm(); }

  ngOnInit(): void {
  }

  addAnalista(analista){
    if (this.formAnalista.valid){
      this.analistaService.createAnalista(this.analista).subscribe((data: {}) => {
      });
    }
    else{
      alert('Llenar todos los campos por favor');
    }
  }

  get usernameAnalista(){
    return this.formAnalista.get('usernameAnalista');
  }

  get estadoAnalista(){
    return this.formAnalista.get('estadoAnalista');
  }

  get claveAnalista(){
    return this.formAnalista.get('claveAnalista');
  }

  get correoElectronicoAnalista(){
    return this.formAnalista.get('correoElectronicoAnalista');
  }

  get rolAnalista(){
    return this.formAnalista.get('rolAnalista');
  }

  createForm(){
    this.formAnalista = this.formBuilder.group({
      usernameAnalista: ['', Validators.required],
      estadoAnalista: ['',  Validators.required],
      claveAnalista: ['', Validators.required],
      rolAnalista: ['', Validators.required],
      correoElectronicoAnalista: ['', [Validators.required, Validators.pattern(this.patronCorreoAnalista)]],
    });
  }
}
