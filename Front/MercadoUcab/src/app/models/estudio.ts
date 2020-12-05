
import { Lugar } from './Lugar';  
import { NivelSocioEconomico } from './nivel-socio-economico';
import { Subcategoria } from './subcategoria';
 
export interface Estudio {
    id: number;
    estado : "string";
    nombre: "string";
    comentarioAnalista : "string";
    edadMinima:number;
    edadMaxima:number;
    fechaInicio: "string";
    fechaFin: "string";
    dtoLugar : Lugar;
    dtoNivelSocioEconomico:NivelSocioEconomico;
    dtoSubcategoria :Subcategoria;
  }


