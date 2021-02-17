import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {MedioConexion} from '../models/medio-conexion';


@Injectable({
  providedIn: 'root'
})
export class MedioConexionService {

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
  getMediosConexion(): Observable<MedioConexion[]>{
    return this.http.get<MedioConexion[]>(this.apiurl + '/medioconexion')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  getMedioConexion(id): Observable<MedioConexion[]>{
    return this.http.get<MedioConexion[]>(this.apiurl + '/medioconexion/' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  createMedioConexion(MedioConexion): Observable<MedioConexion[]>{
    return this.http.post<MedioConexion[]>(this.apiurl + '/medioconexion', JSON.stringify(MedioConexion), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  updateMedioConexion(id, MedioConexion): Observable<MedioConexion[]>{
    return this.http.put<MedioConexion[]>(this.apiurl + '/medioconexion/' + id, JSON.stringify(MedioConexion), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  deleteMedioConexion(id){
    return this.http.delete<MedioConexion[]>(this.apiurl + '/medioconexion/' + id, this.httpOptions)
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

