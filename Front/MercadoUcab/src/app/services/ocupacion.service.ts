import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Ocupacion } from '../models/ocupacion';

@Injectable({
  providedIn: 'root'
})
export class OcupacionService {

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
   getOcupacions():Observable<Ocupacion[]>{
     return this.http.get<Ocupacion[]>(this.apiurl+'/Ocupacion')
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }
   
   getOcupacion(id):Observable<Ocupacion[]>{
     return this.http.get<Ocupacion[]>(this.apiurl+'/Ocupacion/'+id)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }
   
   createOcupacion(Ocupacion):Observable<Ocupacion[]>{
     return this.http.post<Ocupacion[]>(this.apiurl+'/Ocupacion',JSON.stringify(Ocupacion), this.httpOptions)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }
   
   updateOcupacion(id,Ocupacion):Observable<Ocupacion[]>{
     return this.http.put<Ocupacion[]>(this.apiurl+'/Ocupacion/'+id,JSON.stringify(Ocupacion), this.httpOptions)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }
   
   deleteOcupacion(id){
     return this.http.delete<Ocupacion[]>(this.apiurl + '/Ocupacion/' + id, this.httpOptions)
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
