import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Encuesta } from '../models/encuesta';

@Injectable({
  providedIn: 'root'
})
export class EncuestaService {

    // Definimos el url del api
  //apiurl='http://localhost:3000';
  apiurl = 'http://localhost:8080/servicio-1.0-SNAPSHOT/api';
  //apiurl='http://200.109.149.240:8080/servicio-1.0-SNAPSHOT/api';

    constructor(private http: HttpClient) { }
  // Http Options
    httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };



  ///////// Metodos para ejecutar//////////////
  getEncuestas(): Observable<Encuesta[]>{
    return this.http.get<Encuesta[]>(this.apiurl + '/encuesta')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getEncuesta(id): Observable<Encuesta[]>{
    return this.http.get<Encuesta[]>(this.apiurl + '/encuesta/' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getEncuestaEstudio(id): Observable<Encuesta[]>{
    return this.http.get<Encuesta[]>(this.apiurl + 'encuesta' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  createEncuesta(Encuesta): Observable<Encuesta[]>{
    return this.http.post<Encuesta[]>(this.apiurl + '/encuesta', JSON.stringify(Encuesta), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  createPreguntaEncuesta(pregunta): Observable<Encuesta[]>{
    return this.http.post<Encuesta[]>(this.apiurl + '/encuesta', JSON.stringify(pregunta), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  createRespuestaEncuesta(respEncuesta): Observable<any>{
    return this.http.post<any>(this.apiurl + '/encuesta/respuesta', JSON.stringify(respEncuesta), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  createRespuestaEncuestaxAnalista(respEncuesta): Observable<any>{
    return this.http.post<any>(this.apiurl + '/encuesta/respuestaxAnalista', JSON.stringify(respEncuesta), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  updateEncuesta(id, Encuesta): Observable<Encuesta[]>{
    return this.http.put<Encuesta[]>(this.apiurl + '/encuesta/' + id, JSON.stringify(Encuesta), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  deleteEncuesta(id: number){
    return this.http.delete<Encuesta[]>(this.apiurl + '/encuesta/preguntasEncuesta/' + id, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  ///////////////////// Error HandleError
  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
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
