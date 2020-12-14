import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UsuarioService } from 'src/app/services/usuario.service';


@Component({
  selector: 'app-recuperacion',
  templateUrl: './recuperacion.component.html',
  styleUrls: ['./recuperacion.component.css']
})

export class RecuperacionComponent implements OnInit {

@Input() usuario={ correoElectronico:'' };
  formRecuperacion: FormGroup;
 

  constructor(
    private usuarioService:UsuarioService,
    private formBuilder: FormBuilder
    ) {this.createForm()}

  ngOnInit(): void {
  }

  confirmarCorreo(){
    if(this.formRecuperacion.valid){
    this.usuarioService.Logear(this.usuario).subscribe(data=>{
        
    })
    }

    console.log(this.usuario)
  }


  get userCorreo(){return this.formRecuperacion.get('userCorreo');}

  createForm(){
    this.formRecuperacion = this.formBuilder.group({
      userCorreo: ['',Validators.required] 
    });
  }
}
