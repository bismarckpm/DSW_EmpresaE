import {TipoUsuario} from './tipo-usuario';
import {Encuestado} from './encuestado';
import {Cliente} from './cliente';

export interface Usuario {

      _id: number;
      username: string;
      clave: string;
      correoElectronico: string;
      estado: string;

      tipoUsuario: TipoUsuario;


 }





