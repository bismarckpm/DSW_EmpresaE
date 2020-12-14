import { Cliente } from './cliente';
import { Estudio } from './estudio';

export class ClienteEstudio {
    _id:number;
    estado: string;
    comentarioCliente:string;
    cliente:Cliente;
    estudio:Estudio;
}
