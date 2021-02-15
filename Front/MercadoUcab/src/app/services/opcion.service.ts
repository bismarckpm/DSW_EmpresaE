import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Opcion } from '../models/opcion';

@Injectable({
  providedIn: 'root'
})
export class OpcionService {

   //Definimos el url del api
  //apiurl='http://localhost:3000';
  //apiurl = 'http://localhost:8080/servicio-1.0-SNAPSHOT/api';
  apiurl='http://200.109.149.240:8080/servicio-1.0-SNAPSHOT/api';

  constructor(private http:HttpClient) { }
// Http Options
  httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

///////// Metodos para ejecutar//////////////
getOpcions():Observable<Opcion[]>{
  return this.http.get<Opcion[]>(this.apiurl+'/opcion')
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getOpcion(id):Observable<Opcion[]>{
  return this.http.get<Opcion[]>(this.apiurl+'/opcion/'+id)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

createOpcion(Opcion):Observable<Opcion[]>{
  return this.http.post<Opcion[]>(this.apiurl+'/opcion',JSON.stringify(Opcion), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

updateOpcion(id,Opcion):Observable<Opcion[]>{
  return this.http.put<Opcion[]>(this.apiurl+'/opcion/'+id,JSON.stringify(Opcion), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

deleteOpcion(id){
  return this.http.delete<Opcion[]>(this.apiurl + '/opcion/' + id, this.httpOptions)
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
