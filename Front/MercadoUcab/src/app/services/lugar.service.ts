import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Lugar } from '../models/Lugar';

@Injectable({
  providedIn: 'root'
})
export class LugarService {

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
   getLugars():Observable<Lugar[]>{
     return this.http.get<Lugar[]>(this.apiurl+'/lugar')
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   getLugar(id):Observable<Lugar[]>{
     return this.http.get<Lugar[]>(this.apiurl+'/lugar/'+id)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   getEstado(paisID):Observable<Lugar[]>{
    return this.http.get<Lugar[]>(this.apiurl+'/lugar/'+paisID )
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
   }

  getCiudad(paisID, estadoID):Observable<Lugar[]>{
    return this.http.get<Lugar[]>(this.apiurl+'/lugar/'+paisID+estadoID )
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  getMunicipio(paisID, estadoID, ciudadID):Observable<Lugar[]>{
    return this.http.get<Lugar[]>(this.apiurl+'/lugar/'+paisID+estadoID+ciudadID )
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
  }

  getParroquia(paisID, estadoID, ciudadID, municipioID):Observable<Lugar[]>{
    return this.http.get<Lugar[]>(this.apiurl+'/lugar/'+paisID+estadoID+ciudadID+municipioID )
    .pipe(
      retry(1),
      catchError(this.handleError)
    )
   }

   createLugar(Lugar):Observable<Lugar[]>{
     return this.http.post<Lugar[]>(this.apiurl+'/lugar',JSON.stringify(Lugar), this.httpOptions)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   updateLugar(id,Lugar):Observable<Lugar[]>{
     return this.http.put<Lugar[]>(this.apiurl+'/lugar/'+id,JSON.stringify(Lugar), this.httpOptions)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   deleteLugar(id){
     return this.http.delete<Lugar[]>(this.apiurl + '/lugar/' + id, this.httpOptions)
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
