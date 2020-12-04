import { Opcion } from './opcion';
import { Pregunta } from './pregunta';

export interface PreguntaOpcion {
    id:number;
    estado:string;
    dtoPregunta:Pregunta;
    dtoOpcion:Opcion;   

}
