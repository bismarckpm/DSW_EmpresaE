import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  // Definimos el url del api
  //apiurl = 'http://localhost:3000';
  apiurl='http://localhost:8080/servicio-1.0-SNAPSHOT/api';

  constructor(private http: HttpClient) { }

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

///////// Metodos para ejecutar//////////////
  getCategorias(): Observable<Categoria>{
    return this.http.get<Categoria>(this.apiurl + '/categoria')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getCategoria(id): Observable<Categoria>{
    return this.http.get<Categoria>(this.apiurl + '/categoria/' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  createCategoria(categoria): Observable<Categoria>{
    return this.http.post<Categoria>(this.apiurl + '/categoria', JSON.stringify(categoria), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updateCategoria(id, categoria): Observable<Categoria>{
    return this.http.put<Categoria>(this.apiurl + '/categoria/' + id, JSON.stringify(categoria), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  deleteCategoria(id): Observable<Categoria>{
    return this.http.delete<Categoria>(this.apiurl + '/categoria/' + id, this.httpOptions)
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
