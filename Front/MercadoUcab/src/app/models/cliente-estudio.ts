import { Cliente } from './cliente';
import { Estudio } from './estudio';

export interface ClienteEstudio {
    id:number;
    estado: string;
    comentarioCliente:string;
    dtoCliente:Cliente;
    dtoEstudio:Estudio;
}
