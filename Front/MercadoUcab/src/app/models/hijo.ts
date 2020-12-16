import { Encuestado } from './encuestado';
import { Genero } from './genero';

export class Hijo {
    _id:number;
    estado:string;
    fechaNacimiento:string;
    encuestado:Encuestado;
    genero:Genero;
}
