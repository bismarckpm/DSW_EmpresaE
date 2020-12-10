import { Subcategoria } from './subcategoria';
import { TipoPregunta } from './tipo-pregunta';

export interface Pregunta {
    _id: number;
    estado: string;
    descripcion: string;
    dtoTipoPregunta: TipoPregunta;
    dtoSubcategoria: Subcategoria;
}
