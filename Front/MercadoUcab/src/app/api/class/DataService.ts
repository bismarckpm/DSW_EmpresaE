
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from './usuarios/usuario.model';

@Injectable()   
export class DataService {
    urlBase='';
        
    constructor( private httClient: HttpClient){

    }

    //Las funciones que llaman al api Aqui
    usuarioget(usuario: Usuario){
        // adentro nosotros manipularemos el json
        return this.httClient.get(this.urlBase, usuario)
    }

    usuariopost(usuario: Usuario){
         // adentro nosotros manipularemos el json
        return this.httClient.post(this.urlBase, usuario)
    }
}
