import { Component, OnInit } from '@angular/core';
import { Encuestado } from 'src/app/models/encuestado';
import { EncuestadoService } from 'src/app/services/encuestado.service';


@Component({
  selector: 'app-registrarse',
  templateUrl: './registrarse.component.html',
  styleUrls: ['./registrarse.component.css']
})
export class RegistrarseComponent implements OnInit {

  usuario: Encuestado = {
    id: 0,
    nombre: '',
    nombre2: '',
    apellido: '',
    apellido2: '',
    email: '',
    password: '',
    rol: '',
  };

  constructor(private usuarioService: EncuestadoService) {


   }

  ngOnInit(): void {
  }
  // tslint:disable-next-line:typedef
  guardarEncuestado(){
    this.usuarioService.registarEncuestado(this.usuario)
    .subscribe(
      res => {
        console.log(res);
      },
      err => console.error(err)
    );
  }
}
