import { NivelSocioEconomico } from './nivel-socio-economico';

export interface Lugar {
    _id:number;
    estado:string;
    nombre:string;
    tipo:string;
    lugar:Lugar;
    nivelSocioEconomico:NivelSocioEconomico;
}
