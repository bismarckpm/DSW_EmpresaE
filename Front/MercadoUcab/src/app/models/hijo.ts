import { Encuestado } from './encuestado';
import { Genero } from './genero';

export interface Hijo {
    _id:number;
    estado:string;
    fechaNacimiento:string;
    dtoEncuestado:Encuestado;
    dtoGenero:Genero;
}
