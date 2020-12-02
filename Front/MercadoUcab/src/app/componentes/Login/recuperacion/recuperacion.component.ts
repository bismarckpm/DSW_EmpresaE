import { Component, OnInit } from '@angular/core';
import { UsuarioService } from 'src/app/services/usuario.service';


@Component({
  selector: 'app-recuperacion',
  templateUrl: './recuperacion.component.html',
  styleUrls: ['./recuperacion.component.css']
})

export class RecuperacionComponent implements OnInit {

   
 
  constructor(private usuarioService:UsuarioService) { 

  }

  ngOnInit(): void {
  }

  confirmarCorreo(){
    //Validar el correo que este en la base de datos 
    //luego redireccionar a otro componente de para que ingrese la clave
    // guardar
  }
}
