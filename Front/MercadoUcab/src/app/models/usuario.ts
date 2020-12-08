import {TipoUsuario} from './tipo-usuario';
import {Encuestado} from './encuestado';
import {Cliente} from './cliente';

export interface Usuario {

      id: number;
      username: string;
      clave: string;
      correoelectronico: string;
      dtoTipoUsuario: TipoUsuario;
      dtoEncuestado: Encuestado;
      dtoCliente: Cliente;
 }





