import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { TipoPregunta } from '../models/tipo-pregunta';

@Injectable({
  providedIn: 'root'
})
export class TipoPreguntaService {

  // Definimos el url del api
  apiurl = 'http://localhost:3000';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getTipoPreguntas(): Observable<TipoPregunta>{
    return this.http.get<TipoPregunta>(this.apiurl + '/tipopregunta')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getTipoPregunta(id): Observable<TipoPregunta>{
    return this.http.get<TipoPregunta>(this.apiurl + '/tipopregunta/' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  ///////////////////// Error HandleError
  handleError(error): Observable<never> {
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
