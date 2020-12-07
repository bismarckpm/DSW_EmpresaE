import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {Encuestado} from '../models/encuestado';


@Injectable({
  providedIn: 'root'
})
export class EncuestadoService {
  apiurl='http://localhost:3000';

  constructor(private http:HttpClient) { }
// Http Options
  httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

///////// Metodos para ejecutar//////////////
getEncuestados():Observable<Encuestado[]>{
  return this.http.get<Encuestado[]>(this.apiurl+'/Encuestado')
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getEncuestado(id):Observable<Encuestado[]>{
  return this.http.get<Encuestado[]>(this.apiurl+'/Encuestado/'+id)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

createEncuestado(Encuestado):Observable<Encuestado[]>{
  return this.http.post<Encuestado[]>(this.apiurl+'/Encuestado',JSON.stringify(Encuestado), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

updateEncuestado(id,Encuestado):Observable<Encuestado[]>{
  return this.http.put<Encuestado[]>(this.apiurl+'/Encuestado/'+id,JSON.stringify(Encuestado), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

deleteEncuestado(id){
  return this.http.delete<Encuestado[]>(this.apiurl + '/Encuestado/' + id, this.httpOptions)
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
