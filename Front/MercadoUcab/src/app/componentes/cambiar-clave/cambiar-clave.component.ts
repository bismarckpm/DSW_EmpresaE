import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { parse } from 'path';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-cambiar-clave',
  templateUrl: './cambiar-clave.component.html',
  styleUrls: ['./cambiar-clave.component.css']
})
export class CambiarClaveComponent implements OnInit {
  
  @Input() usuario={ _id:0,claveNueva:"",clave:'' };

  constructor(
    public usuarioService: UsuarioService,
    public router:Router

  ) { }

  ngOnInit(): void {
  }
  

  CambioDeClave(clavenueva){
    this.HacerUsuario(clavenueva);
    this.usuarioService.Logear(this.usuario).subscribe(data=>{
        if(data[0].respuesta=="Cambio Satisfactorio"){
          window.alert("Cambio Realizado");
          this.router.navigate(['/Login']);
        }else{
          window.alert("Ups.. No te encontramos, Por favor rivisa tu correo o Registrate");
        }
    })

  }

  HacerUsuario(clavenueva){
    this.usuario._id= parseInt(localStorage.getItem('usuarioID'));
    this.usuarioService.getUsuario(this.usuario._id).subscribe(data=>{
          this.usuario.clave=data[0].clave;   
    })
    this.usuario.claveNueva= clavenueva;
    console.log(this.usuario);

}

}
