import { Genero } from './genero';

export interface Hijo {
    id:number;
    estado:string;
    fechaNacimiento:string;
    dtoEncuestado:Encuestado;
    dtoGenero:Genero;
}
