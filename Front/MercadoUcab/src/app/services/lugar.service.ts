import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Lugar } from '../models/lugar';
import {Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class LugarService {

  apiurl = 'http://localhost:3000';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
///////// Metodos para ejecutar//////////////

  getLugares(): Observable<Lugar>{
    return this.http.get<Lugar>(this.apiurl + '/lugar')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  getLugar(id): Observable<Lugar>{
    return this.http.get<Lugar>(this.apiurl + '/lugar/' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  createLugar(lugar): Observable<Lugar>{
    return this.http.post<Lugar>(this.apiurl + '/lugar', JSON.stringify(lugar), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  updateLugar(id, lugar): Observable<Lugar>{
    return this.http.put<Lugar>(this.apiurl + '/lugar/' + id, JSON.stringify(lugar), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  deleteLugar(id){
    return this.http.delete<Lugar>(this.apiurl + '/lugar/' + id, this.httpOptions)
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
