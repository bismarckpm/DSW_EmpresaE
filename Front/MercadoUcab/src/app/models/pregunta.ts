import { Opcion } from './opcion';
import { Subcategoria } from './subcategoria';
import { TipoPregunta } from './tipo-pregunta';

export class Pregunta {
    _id: number;
    estado: string;
    descripcion: string;
    tipo: TipoPregunta;
    subcategoria: Subcategoria;
    opciones: Opcion[];

    constructor(id: number){
        this._id = id;
    }
}
