import {TipoUsuario} from './tipo-usuario';


export  interface Usuario {

      _id: number;
      username: string;
      clave: string;
      correoElectronico: string;
      estado: string;
      tipoUsuario: TipoUsuario;
      autenticacion:string;
      rol :string;
      respuesta:string;
      nuevaClave:string;
 }





