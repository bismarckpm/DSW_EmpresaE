import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {EstadoCivil} from '../models/estado-civil';
import {NivelAcademico} from '../models/nivel-academico';


@Injectable({
  providedIn: 'root'
})
export class NivelAcademicoService {
  apiurl = 'http://localhost:3000';

  constructor(private http: HttpClient) { }
// Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

///////// Metodos para ejecutar//////////////
  getNivelesAcademicos(): Observable<NivelAcademico[]>{
    return this.http.get<NivelAcademico[]>(this.apiurl + '/nivelacademico')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  getNivelAcademico(id): Observable<NivelAcademico[]>{
    return this.http.get<EstadoCivil[]>(this.apiurl + '/nivelacademico/' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  createNivelAcademico(NivelAcademico): Observable<EstadoCivil[]>{
    return this.http.post<NivelAcademico[]>(this.apiurl + '/nivelacademico', JSON.stringify(NivelAcademico), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  updateNivelAcademico(id, NivelAcademico): Observable<NivelAcademico[]>{
    return this.http.put<NivelAcademico[]>(this.apiurl + '/nivelacademico/' + id, JSON.stringify(NivelAcademico), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  deleteNivelAcademico(id){
    return this.http.delete<NivelAcademico[]>(this.apiurl + '/nivelacademico/' + id, this.httpOptions)
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

