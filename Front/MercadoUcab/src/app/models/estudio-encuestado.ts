import { Encuestado } from './encuestado';
import { Estudio } from './estudio';

export interface EstudioEncuestado {
    id:number;
    estado:string;
    fechaRealizacion:string;
    dtoEncuestado:Encuestado;
    dtoEstudio:Estudio;
    
}
