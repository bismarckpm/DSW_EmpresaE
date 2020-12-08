import { Encuestado } from './encuestado';
import { Estudio } from './estudio';

export interface EstudioEncuestado {
    _id:number;
    estado:string;
    fechaRealizacion:string;
    dtoEncuestado:Encuestado;
    dtoEstudio:Estudio;
    
}
