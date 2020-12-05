import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Usuario } from '../models/usuario';
import {Observable, throwError} from 'rxjs';
import {catchError, retry} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  apiurl = 'http://localhost:3000';

  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
///////// Metodos para ejecutar//////////////

  getUsuarios(): Observable<Usuario>{
    return this.http.get<Usuario>(this.apiurl + '/usuario')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  getUsuario(id): Observable<Usuario>{
    return this.http.get<Usuario>(this.apiurl + '/usuario/' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  createUsuario(usuario): Observable<Usuario>{
    return this.http.post<Usuario>(this.apiurl + '/usuario', JSON.stringify(usuario), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  updateUsuario(id, usuario): Observable<Usuario>{
    return this.http.put<Usuario>(this.apiurl + '/usuario/' + id, JSON.stringify(usuario), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  deleteUsuario(id){
    return this.http.delete<Usuario>(this.apiurl + '/usuario/' + id, this.httpOptions)
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
