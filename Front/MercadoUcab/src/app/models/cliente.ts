import { Lugar } from './Lugar';
import { Usuario } from './usuario';
import {Telefono} from "./telefono";

export class Cliente {
    _id: number;
    estado: string;
    razonSocial: string;
    rif: number;
    telefono: Telefono;

    lugar:Lugar;
    usuario:Usuario;

}
