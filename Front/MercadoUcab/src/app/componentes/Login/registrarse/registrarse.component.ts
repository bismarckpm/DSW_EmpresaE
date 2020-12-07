import {Component, Input, OnInit} from '@angular/core';
import {EncuestadoService} from '../../../services/encuestado.service';
import {Encuestado} from '../../../models/encuestado';

@Component({
  selector: 'app-registrarse',
  templateUrl: './registrarse.component.html',
  styleUrls: ['./registrarse.component.css']
})
export class RegistrarseComponent implements OnInit {

  @Input() encuestado = {id: 0, primerNombre: '', segundoNombre: '' , primerApellido: '', segundoApellido: 0, fechaNacimiento: '', estado: '',
    dtoEstadoCivil: {id: 0, nombre: '', estado: ''},
    dtoNivelAcademico: {id: 0, nombre: '', estado: ''},
    dtoMedioConexion: {id: 0, nombre: '', estado: ''},
    dtoGenero: {id: 0, nombre: '', estado: ''},
    dtoOcupacion: {id: 0, nombre: '', estado: ''},
    dtoNivelSocioEconomico: {id: 0, nombre: '', estado: '', descripcion: ''},
    dtoLugar: {id: 0, estado: '', nombre: '', tipo: ''},
    dtoUsuario: {id: 0, username: '', estado: '', clave: '', correoElectronico: '', dtoTipoUsuario: {id: 0, estado: '', descripcion: ''}},
  };

  estadoCivil: any;
  nivelAcademico: any;
  medioConexion: any;
  genero: any;
  ocupacion: any;
  nivelSocioEconomico: any;
  lugar: any;
  usuario: any;

  constructor(private encuestadoService: EncuestadoService) {
  }

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  guardarEncuestado() {
   /* this.encuestadoService.registarEncuestado(this.encuestado)
      .subscribe(
        res => {
          console.log(res);
        },
        err => console.error(err)
      );*/
  }
}
