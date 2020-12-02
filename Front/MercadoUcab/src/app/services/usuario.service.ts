import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../models/usuario';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  API_URI = 'http://';
  usuarios: Usuario[] = [
    {
      id: 0,
      nombre: 'prueba0',
      nombre2: '',
      apellido: '',
      apellido2: '',
      email: '',
      password: '',
      rol: '',
    },
    {
      id: 1,
      nombre: 'prueba1',
      nombre2: '',
      apellido: '',
      apellido2: '',
      email: '',
      password: '',
      rol: '',
    },
  ];

  constructor( private http: HttpClient) { }

  getUsuarios(): Usuario[] {
    return this.usuarios;
  }

  getUsuario(){
    return this.http.get(`${this.API_URI}/usuario`);
  }

  registarUsuario(usuario: Usuario){
    return this.http.post(`${this.API_URI}/usuario`, usuario);
  }

  updateUsuario(id , updateUser){
    return this.http.post(`${this.API_URI}/usuario/${id}`, updateUser);
  }
}
