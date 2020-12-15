import { Usuario } from './usuario';

export class Administrador{
    
    _id: number;
    username: string;
    clave: string;
    estado: string;
    correoElectronico: string;
    usuario:Usuario;
}