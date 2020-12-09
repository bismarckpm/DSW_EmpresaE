import { Lugar } from './Lugar';
import { Usuario } from './usuario';

export interface Cliente {
    _id: number;
    estado: string;
    razonSocial: string;
    rif: number;

    dtoLugar:Lugar;
    dtoUsuairo:Usuario;

}
