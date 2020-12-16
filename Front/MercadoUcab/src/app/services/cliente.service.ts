import { Injectable } from '@angular/core';
import { Cliente } from '../models/cliente';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  secciones: string[] = [
    'Inicio',
    'Perfil',
    'Estudios',
  ];


  apiurl='http://localhost:3000';
  //apiurl='http://localhost:8080/servicio-1.0-SNAPSHOT/api';


  constructor(private http:HttpClient) { }
  // Http Options
    httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }



  ////////////////////Metodos para el sidbar//////////////////////

  getSecciones(): string[] {
    return this.secciones;
  }

  getSeccion(i): string {
    return this.secciones[i];
  }


  ///////// Metodos para ejecutar//////////////
  getClientes():Observable<Cliente[]>{
    return this.http.get<Cliente[]>(this.apiurl+'/cliente')
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  getClienteDelUsuario(usuario):Observable<Cliente[]>{
    return this.http.get<Cliente[]>(this.apiurl+'/usuario/'+usuario)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  getCliente(id):Observable<Cliente[]>{
    return this.http.get<Cliente[]>(this.apiurl+'/cliente/'+id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  createCliente(Cliente):Observable<Cliente[]>{
    return this.http.post<Cliente[]>(this.apiurl+'/cliente',JSON.stringify(Cliente), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  updateCliente(id,Cliente):Observable<Cliente[]>{
    return this.http.put<Cliente[]>(this.apiurl+'/cliente/update/'+id,JSON.stringify(Cliente), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  deleteCliente(id){
    return this.http.delete<Cliente[]>(this.apiurl + '/cliente/' + id, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
  }

  ///////////////////// Error HandleError
  handleError(error) {
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
