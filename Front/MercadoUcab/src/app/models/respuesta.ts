import { Encuestado } from './encuestado';
import { PreguntaOpcion } from './pregunta-opcion';

export interface Respuesta {
    id:number;
    estado:string;
    texto:string;
    dtoPreguntaOpcion:PreguntaOpcion;
    dtoEncuestado:Encuestado;
}
