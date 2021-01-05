import { Injectable } from '@angular/core';
import {Presentacion} from '../models/presentacion';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PresentacionService {

  //apiurl='http://localhost:3000';
  apiurl = 'http://localhost:8080/servicio-1.0-SNAPSHOT/api';

  constructor(private http:HttpClient) { }
// Http Options
  httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

///////// Metodos para ejecutar//////////////
getPresentaciones():Observable<Presentacion[]>{
  return this.http.get<Presentacion[]>(this.apiurl+'/presentacion')
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getPresentacion(id):Observable<Presentacion[]>{
  return this.http.get<Presentacion[]>(this.apiurl+'/presentacion/'+id)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

createPresentacion(Presentacion):Observable<Presentacion[]>{
  return this.http.post<Presentacion[]>(this.apiurl+'/presentacion',JSON.stringify(Presentacion), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

updatePresentacion(id,Presentacion):Observable<Presentacion[]>{
  return this.http.put<Presentacion[]>(this.apiurl+'/presentacion/'+id,JSON.stringify(Presentacion), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

deletePresentacion(id){
  return this.http.delete<Presentacion[]>(this.apiurl + '/presentacion/' + id, this.httpOptions)
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
