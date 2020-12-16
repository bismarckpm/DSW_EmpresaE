import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { NivelSocioEconomico } from '../models/nivel-socio-economico';

@Injectable({
  providedIn: 'root'
})
export class NivelSocioEconomicoService {

     //Definimos el url del api
    //apiurl='http://localhost:8080/servicio-1.0-SNAPSHOT/api';
    apiurl = 'http://localhost:3000';

     constructor(private http:HttpClient) { }
   // Http Options
     httpOptions = {
     headers: new HttpHeaders({
       'Content-Type': 'application/json'
     })
   }

   ///////// Metodos para ejecutar//////////////
   getNivelesSocioEconomicos():Observable<NivelSocioEconomico[]>{
     return this.http.get<NivelSocioEconomico[]>(this.apiurl+'/nivelsocioeconomico')
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   getNivelSocioEconomico(id):Observable<NivelSocioEconomico[]>{
     return this.http.get<NivelSocioEconomico[]>(this.apiurl+'/nivelsocioeconomico/'+id)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   createNivelSocioEconomico(NivelSocioEconomico):Observable<NivelSocioEconomico[]>{
     return this.http.post<NivelSocioEconomico[]>(this.apiurl+'/nivelsocioeconomico',JSON.stringify(NivelSocioEconomico), this.httpOptions)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   updateNivelSocioEconomico(id,NivelSocioEconomico):Observable<NivelSocioEconomico[]>{
     return this.http.put<NivelSocioEconomico[]>(this.apiurl+'/nivelsocioeconomico/'+id,JSON.stringify(NivelSocioEconomico), this.httpOptions)
     .pipe(
       retry(1),
       catchError(this.handleError)
     )
   }

   deleteNivelSocioEconomico(id){
     return this.http.delete<NivelSocioEconomico[]>(this.apiurl + '/nivelsocioeconomico/' + id, this.httpOptions)
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
