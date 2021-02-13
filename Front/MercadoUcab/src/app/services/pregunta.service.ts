import { Injectable } from '@angular/core';
import { Pregunta } from '../models/pregunta';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PreguntaService {

  // apiurl = 'http://localhost:8080/servicio-1.0-SNAPSHOT/api';
  apiurl = 'http://localhost:3000';

  constructor(private http: HttpClient) { }
  // Http Options
    httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  ///////// Metodos para ejecutar//////////////
  getPreguntas(): Observable<Pregunta[]>{
    return this.http.get<Pregunta[]>(this.apiurl + '/pregunta')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getPregunta(id): Observable<Pregunta[]>{
    return this.http.get<Pregunta[]>(this.apiurl + '/pregunta/' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  // concatenar el ID del estudio que pide el endpoint
  getPreguntasResponder(idEstudio): Observable<Pregunta[]>{
    return this.http.get<Pregunta[]>(this.apiurl + '/pregunta/encuestaEstudio/' + idEstudio)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  // recibo el idEstudio y lo concateno al path de la peticion que devuelve una lista
  // de preguntas activas asociada a la subcategoria del estudio
  // getPreguntasXSubcategoria(idEstudio): Observable<Pregunta[]>{
  //   return this.http.get<Pregunta[]>(this.apiurl + '/pregunta/preguntasSubcategoria/' + idEstudio)
  //   .pipe(
  //     retry(1),
  //     catchError(this.handleError)
  //   );
  // }

  // FRONT
  getPreguntasXSubcategoria(idEstudio): Observable<Pregunta[]>{
    return this.http.get<Pregunta[]>(this.apiurl + '/pregunta')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  sugerirPreguntas(idEstudio): Observable<Pregunta[]>{
    // return this.http.get<Pregunta[]>(this.apiurl + '/pregunta/sugerirPreguntas/' + idEstudio)
    return this.http.get<Pregunta[]>(this.apiurl + '/pregunta')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  createPregunta(Pregunta): Observable<Pregunta[]>{
    return this.http.post<Pregunta[]>(this.apiurl + '/pregunta', JSON.stringify(Pregunta), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updatePregunta(id, Pregunta): Observable<Pregunta[]>{
    console.log('=========Imprimiendo el objeto=============');
    console.log(Pregunta);
    console.log('=========Imprimiendo el objeto=============');

    return this.http.put<Pregunta[]>(this.apiurl + '/pregunta/' + id, JSON.stringify(Pregunta), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  deletePregunta(id){
    return this.http.delete<Pregunta[]>(this.apiurl + '/pregunta/' + id, this.httpOptions)
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
