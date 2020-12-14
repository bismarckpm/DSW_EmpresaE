import { NivelSocioEconomico } from './nivel-socio-economico';

export class Lugar {
    _id:number;
    estado:string;
    nombre:string;
    tipo:string;
    lugar:Lugar;
    nivelSocioEconomico:NivelSocioEconomico;
}
