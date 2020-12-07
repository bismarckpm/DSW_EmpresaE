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
  apiurl = 'http://localhost:3000';

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getEncuestas(): Observable<Encuesta>{
    return this.http.get<Encuesta>(this.apiurl + '/encuesta')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getEncuesta(id): Observable<Encuesta>{
    return this.http.get<Encuesta>(this.apiurl + '/encuesta/' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  createEncuesta(encuesta): Observable<Encuesta>{
    return this.http.post<Encuesta>(this.apiurl + '/encuesta', JSON.stringify(encuesta), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updateEncuesta(id, encuesta): Observable<Encuesta>{
    return this.http.put<Encuesta>(this.apiurl + '/encuesta/' + id, JSON.stringify(encuesta), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  deleteEncuesta(id): Observable<Encuesta> {
    return this.http.delete<Encuesta>(this.apiurl + '/encuesta/' + id, this.httpOptions)
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
