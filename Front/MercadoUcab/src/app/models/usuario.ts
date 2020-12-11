import {TipoUsuario} from './tipo-usuario';
import {Encuestado} from './encuestado';
import {Cliente} from './cliente';
import { Interface } from 'readline';

export  interface Usuario {

      _id: number;
      username: string;
      clave: string;
      correoElectronico: string;
      estado: string;
      tipoUsuario: TipoUsuario;
      autenticacion:string;
 }





