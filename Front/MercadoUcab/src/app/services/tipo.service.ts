import { Injectable } from '@angular/core';
import {Tipo} from '../models/tipo';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TipoService {

  //apiurl = 'http://localhost:8080/servicio-1.0-SNAPSHOT/api';
  apiurl = 'http://localhost:3000';

  constructor(private http: HttpClient) { }

// Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  ///////// Metodos para ejecutar//////////////
  getTipos(): Observable<Tipo[]>{
    return this.http.get<Tipo[]>(this.apiurl + '/tipo')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  getTipo(id): Observable<Tipo[]>{
    return this.http.get<Tipo[]>(this.apiurl + '/tipo/' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  createTipo(Tipo): Observable<Tipo[]>{
    return this.http.post<Tipo[]>(this.apiurl + '/tipo', JSON.stringify(Tipo), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  updateTipo(id, Tipo): Observable<Tipo[]>{
    return this.http.put<Tipo[]>(this.apiurl + '/tipo/' + id, JSON.stringify(Tipo), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  deleteTipo(id){
    return this.http.delete<Tipo[]>(this.apiurl + '/tipo/' + id, this.httpOptions)
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
