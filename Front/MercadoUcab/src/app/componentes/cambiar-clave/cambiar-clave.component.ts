import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { parse } from 'path';
import { UsuarioService } from 'src/app/services/usuario.service';
import {ToastService} from "../../services/toast.service";

@Component({
  selector: 'app-cambiar-clave',
  templateUrl: './cambiar-clave.component.html',
  styleUrls: ['./cambiar-clave.component.css']
})
export class CambiarClaveComponent implements OnInit {

  aux: any;

  @Input() usuario={ claveNueva:"" };
  @Input() auxUsuario={ claveNueva:"" };

  constructor(
    public usuarioService: UsuarioService,
    public router:Router,
    private toast: ToastService

  ) { }

  ngOnInit(): void {
  }


  CambioDeClave(){

    this.usuarioService.Logear(this.usuario).subscribe(data=>{
        if(data[0].respuesta=="Cambio Satisfactorio"){
          window.alert("Cambio Realizado");
          this.router.navigate(['/Login']);
        }else{
          window.alert("Ups.. No te encontramos, Por favor rivisa tu correo o Registrate");
        }
    })

  }




}
