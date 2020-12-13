import { Estudio } from './estudio';
import { Pregunta } from './pregunta';

export class Encuesta {
    _id: number;
    estado: string;
    fechaInicio: string;
    fechaFin: string;
    estudio: Estudio;
    pregunta: Pregunta;
}
