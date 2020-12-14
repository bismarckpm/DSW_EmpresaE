import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {Encuestado} from '../models/encuestado';


@Injectable({
  providedIn: 'root'
})
export class EncuestadoService {

  secciones: string[] = [
    'Perfil', 
    'Encuestas',

  ];
  /// apiurl='http://localhost:8080/servicio-1.0-SNAPSHOT/api';
  apiurl = 'http://localhost:3000';

  constructor(private http:HttpClient) { }
// Http Options
  httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}


////////////// Sidbard////////////
getSecciones(): string[] {
  return this.secciones;
}

getSeccion(i): string {
  return this.secciones[i];
}


///////// Metodos para ejecutar//////////////
getEncuestados():Observable<Encuestado[]>{
  return this.http.get<Encuestado[]>(this.apiurl+'/encuestado')
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getEncuestado(id):Observable<Encuestado[]>{
  return this.http.get<Encuestado[]>(this.apiurl+'/encuestado/'+id)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getEncuestadoDelUsuario(idUsuario):Observable<Encuestado[]>{
  return this.http.get<Encuestado[]>(this.apiurl+'/usuario/'+idUsuario)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

createEncuestado(Encuestado):Observable<Encuestado[]>{
  return this.http.post<Encuestado[]>(this.apiurl+'/encuestado',JSON.stringify(Encuestado), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

updateEncuestado(id,Encuestado):Observable<Encuestado[]>{
  return this.http.put<Encuestado[]>(this.apiurl+'/encuestado/'+id,JSON.stringify(Encuestado), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

deleteEncuestado(id){
  return this.http.delete<Encuestado[]>(this.apiurl + '/encuestado/' + id, this.httpOptions)
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
