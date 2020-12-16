import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Marca } from '../models/marca';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MarcaService {
   //Definimos el url del api
  apiurl='http://localhost:3000';
  //  apiurl='http://localhost:8080/servicio-1.0-SNAPSHOT/api';


  constructor(private http:HttpClient) { }

// Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }



  ///////// Metodos para ejecutar//////////////
getMarcas():Observable<Marca[]>{
  return this.http.get<Marca[]>(this.apiurl+'/marca')
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getMarca(id):Observable<Marca[]>{
  return this.http.get<Marca[]>(this.apiurl+'/marca/'+id)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

createMarca(Marca):Observable<Marca[]>{
  return this.http.post<Marca[]>(this.apiurl+'/marca',JSON.stringify(Marca), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

updateMarca(id,Marca):Observable<Marca[]>{
  return this.http.put<Marca[]>(this.apiurl+'/marca/'+id,JSON.stringify(Marca), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

deleteMarca(id){
  return this.http.delete<Marca[]>(this.apiurl + '/marca/' + id, this.httpOptions)
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
