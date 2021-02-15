import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Genero } from '../models/genero';

@Injectable({
  providedIn: 'root'
})
export class GeneroService {

     //Definimos el url del api

     //apiurl='http://localhost:8080/servicio-1.0-SNAPSHOT/api';
     //apiurl = 'http://localhost:3000';
  apiurl='http://200.109.149.240:8080/servicio-1.0-SNAPSHOT/api';

     constructor(private http:HttpClient) { }
   // Http Options
     httpOptions = {
     headers: new HttpHeaders({
       'Content-Type': 'application/json'
     })
   }

   ///////// Metodos para ejecutar//////////////
   getGeneros():Observable<Genero[]>{
     return this.http.get<Genero[]>(this.apiurl+'/genero')
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   getGenero(id):Observable<Genero[]>{
     return this.http.get<Genero[]>(this.apiurl+'/genero/'+id)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   createGenero(Genero):Observable<Genero[]>{
     return this.http.post<Genero[]>(this.apiurl+'/genero',JSON.stringify(Genero), this.httpOptions)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   updateGenero(id,Genero):Observable<Genero[]>{
     return this.http.put<Genero[]>(this.apiurl+'/genero/'+id,JSON.stringify(Genero), this.httpOptions)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   deleteGenero(id){
     return this.http.delete<Genero[]>(this.apiurl + '/genero/' + id, this.httpOptions)
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
