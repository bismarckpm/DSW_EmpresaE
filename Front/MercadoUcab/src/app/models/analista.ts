import { Usuario } from './usuario';
import {TipoUsuario} from './tipo-usuario';

export class Analista {

  _id: number;
  usuario: Usuario;
  username:string;
  correoelectronico: string;
  tipoUsuario: TipoUsuario;

}
