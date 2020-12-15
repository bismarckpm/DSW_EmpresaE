import { Lugar } from './Lugar';
import { Usuario } from './usuario';

export class Cliente {
    _id: number;
    estado: string;
    razonSocial: string;
    rif: number;
    telefono: string;

    lugar:Lugar;
    usuario:Usuario;

}
