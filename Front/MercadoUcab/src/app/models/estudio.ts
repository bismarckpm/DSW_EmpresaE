
import { Lugar } from './Lugar';
import { NivelSocioEconomico } from './nivel-socio-economico';
import { Subcategoria } from './subcategoria';
import {Analista} from './analista';
import { Genero } from './genero';

export class Estudio {
    _id: number;
    estado: string;
    estadoEstudioEncuestado: string;
    nombre: string;
    comentarioAnalista: string;
    edadMinima: number;
    edadMaxima: number;
    genero: Genero;
    fechaInicio: string;
    fechaFin: string;
    lugar: Lugar;
    nivelSocioEconomico: NivelSocioEconomico;
    subcategoria: Subcategoria;
    analista: Analista;
}


