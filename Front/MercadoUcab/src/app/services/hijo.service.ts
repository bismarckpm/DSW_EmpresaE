import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Hijo } from '../models/hijo';

@Injectable({
  providedIn: 'root'
})
export class HijoService {

   //Definimos el url del api
   apiurl='http://localhost:3000';
   
  constructor(private http:HttpClient) { }
// Http Options
  httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
} 

///////// Metodos para ejecutar//////////////
getHijos():Observable<Hijo[]>{
  return this.http.get<Hijo[]>(this.apiurl+'/Hijo')
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getHijo(id):Observable<Hijo[]>{
  return this.http.get<Hijo[]>(this.apiurl+'/Hijo/'+id)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

createHijo(Hijo):Observable<Hijo[]>{
  return this.http.post<Hijo[]>(this.apiurl+'/Hijo',JSON.stringify(Hijo), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

updateHijo(id,Hijo):Observable<Hijo[]>{
  return this.http.put<Hijo[]>(this.apiurl+'/Hijo/'+id,JSON.stringify(Hijo), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

deleteHijo(id){
  return this.http.delete<Hijo[]>(this.apiurl + '/Hijo/' + id, this.httpOptions)
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
