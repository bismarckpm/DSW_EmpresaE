import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../models/usuario/usuario';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  API_URI= 'http://';

  constructor( private http: HttpClient) { }

  getUsuario(){
      return this.http.get(`${this.API_URI}/usuario`);
  }
  
  registarUsuario(usuario: Usuario){
    return this.http.post(`${this.API_URI}/usuario`, usuario);
  }

  updateUsuario(id ,updateUser){
    return this.http.post(`${this.API_URI}/usuario/${id}`,updateUser);
  }
}
