import { Opcion } from './opcion';
import { Pregunta } from './pregunta';

export interface PreguntaOpcion {
    _id:number;
    estado:string;
    pregunta:Pregunta;
    opcion:Opcion;

}
