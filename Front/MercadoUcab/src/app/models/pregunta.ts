import { Subcategoria } from './subcategoria';
import { TipoPregunta } from './tipo-pregunta';

export interface Pregunta {
    id: number;
    estado: string;
    descripcion: string;
    dtoTipoPregunta:TipoPregunta;
    dtoSubcategoria:Subcategoria;
}
