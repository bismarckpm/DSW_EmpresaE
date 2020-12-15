import { EstadoCivil } from './estado-civil';
import { Genero } from './genero';
import { Lugar } from './Lugar';
import { MedioConexion } from './medio-conexion';
import { NivelAcademico } from './nivel-academico';
import { NivelSocioEconomico } from './nivel-socio-economico';
import { Ocupacion } from './ocupacion';
import { Usuario } from './usuario';

export class Encuestado {
   _id:number;
   primerNombre:string;
   segundoNombre:string;
   primerApellido:string;
   segundoApellido:string;
   fechaNacimiento:string;
   telefono: string;

   estado:EstadoCivil;
   nivelAcademico:NivelAcademico;
   medioConexion:MedioConexion;
   genero:Genero;
   ocupacion:Ocupacion;
   nivelSocioeconomico:NivelSocioEconomico;
   lugar:Lugar;
   usuario:Usuario;

}
