import { Encuestado } from './encuestado';
import { PreguntaOpcion } from './pregunta-opcion';

export interface Respuesta {
    _id:number;
    estado:string;
    texto:string;
    preguntaOpcion:PreguntaOpcion;
    encuestado:Encuestado;
}
