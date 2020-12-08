import { EstadoCivil } from './estado-civil';
import { Genero } from './genero';
import { Lugar } from './Lugar';
import { MedioConexion } from './medio-conexion';
import { NivelAcademico } from './nivel-academico';
import { NivelSocioEconomico } from './nivel-socio-economico';
import { Ocupacion } from './ocupacion';
import { Usuario } from './usuario';

export interface Encuestado {
<<<<<<< HEAD
   _id:number;
   primerNombre:string;
   segundoNombre:string;
   primerApeliido:string;
   segundoApeliido:string;
   fechaNacimiento:string;
   
   dtoEstado:EstadoCivil;
   dtoNivelAcademico:NivelAcademico;
   dtoMedioConexion:MedioConexion;
   dtoGenero:Genero;
   dtoOcupacion:Ocupacion;
   dtoNivelSocioeconomico:NivelSocioEconomico;
   dtoLugar:Lugar;
   dtoUsuario:Usuario;
=======
   id: number;
   primerNombre: string;
   segundoNombre: string;
   primerApeliido: string;
   segundoApeliido: string;
   fechaNacimiento: string;

   dtoEstado: EstadoCivil;
   dtoNivelAcademico: NivelAcademico;
   dtoMedioConexion: MedioConexion;
   dtoGenero: Genero;
   dtoOcupacion: Ocupacion;
   dtoNivelSocioeconomico: NivelSocioEconomico;
   dtoLugar: Lugar;
   dtoUsuario: Usuario;
>>>>>>> 5f372d4500a99251b57725f05e4aa82c84016e55

}
