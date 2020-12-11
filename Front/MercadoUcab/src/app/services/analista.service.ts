import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Analista } from '../models/analista';

@Injectable({
  providedIn: 'root'
})
export class AnalistaService {

  apiurl = 'http://localhost:3000';

  constructor(private http: HttpClient) { }
// Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
  })
};

///////// Metodos para ejecutar//////////////
  getAnalistas(): Observable<Analista[]>{
    return this.http.get<Analista[]>(this.apiurl + '/analista')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getAnalista(id): Observable<Analista[]>{
    return this.http.get<Analista[]>(this.apiurl + '/analista/' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  createAnalista(analista): Observable<Analista[]>{
    return this.http.post<Analista[]>(this.apiurl + '/analista', JSON.stringify(analista), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updateAnalista(id, analista): Observable<Analista[]>{
    return this.http.put<Analista[]>(this.apiurl + '/analista/' + id, JSON.stringify(analista), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  deleteAnalista(id){
    return this.http.delete<Analista[]>(this.apiurl + '/analista/' + id, this.httpOptions)
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
