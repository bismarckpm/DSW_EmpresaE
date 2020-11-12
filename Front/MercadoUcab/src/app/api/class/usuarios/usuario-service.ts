import { Injectable } from '@angular/core';
import { Usuario } from './usuario.model';
import { DataService } from '../DataService';

@Injectable()
export class UsuarioService {

    usuario: Usuario[] = [];

    constructor(private dataservice: DataService){
        
        
    }
}
