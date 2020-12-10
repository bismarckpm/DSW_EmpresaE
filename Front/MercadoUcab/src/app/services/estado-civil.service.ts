import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {EstadoCivil} from '../models/estado-civil';


@Injectable({
  providedIn: 'root'
})
export class EstadoCivilService {
  apiurl = 'http://localhost:3000';

  constructor(private http: HttpClient) { }
// Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

///////// Metodos para ejecutar//////////////
  getEstadosCiviles(): Observable<EstadoCivil[]>{
    return this.http.get<EstadoCivil[]>(this.apiurl + '/estadocivil')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  getEstadoCivil(id): Observable<EstadoCivil[]>{
    return this.http.get<EstadoCivil[]>(this.apiurl + '/estadocivil/' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  createEstadoCivil(EstadoCivil): Observable<EstadoCivil[]>{
    return this.http.post<EstadoCivil[]>(this.apiurl + '/estadocivil', JSON.stringify(EstadoCivil), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  updateEstadoCivil(id, EstadoCivil): Observable<EstadoCivil[]>{
    return this.http.put<EstadoCivil[]>(this.apiurl + '/estadocivil/' + id, JSON.stringify(EstadoCivil), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  deleteEstadoCivil(id){
    return this.http.delete<EstadoCivil[]>(this.apiurl + '/estadocivil/' + id, this.httpOptions)
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
