import { Encuestado } from './encuestado';
import { PreguntaOpcion } from './pregunta-opcion';

export class Respuesta {
    _id:number;
    estado:string;
    texto:string;
    preguntaOpcion:PreguntaOpcion;
    encuestado:Encuestado;
}
