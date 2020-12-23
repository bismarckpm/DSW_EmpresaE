
import { Lugar } from './Lugar';
import { NivelSocioEconomico } from './nivel-socio-economico';
import { Subcategoria } from './subcategoria';
import {Analista} from './analista';

export class Estudio {
    _id: number;
    estado: string;
    nombre: string;
    comentarioAnalista: string;
    edadMinima: number;
    edadMaxima: number;
    fechaInicio: string;
    fechaFin: string;
    lugar: Lugar;
    nivelSocioEconomico: NivelSocioEconomico;
    subcategoria: Subcategoria;
    analista: Analista;
}


