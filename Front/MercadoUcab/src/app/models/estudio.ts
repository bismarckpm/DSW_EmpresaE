
import { Lugar } from './Lugar';  
import { NivelEconomico } from './nivel-economico';
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
    dtoNivelSocioEconomico:NivelEconomico;
    dtoSubcategoria :Subcategoria;
  }


