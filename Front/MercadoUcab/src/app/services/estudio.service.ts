import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Estudio } from '../models/estudio';
import {Encuestado} from "../models/encuestado";

@Injectable({
  providedIn: 'root'
})
export class EstudioService {


  //apiurl = 'http://localhost:3000';
   apiurl='http://localhost:8080/servicio-1.0-SNAPSHOT/api';
 

  constructor(private http: HttpClient) { }


  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getEstudios(): Observable<Estudio[]>{
    return this.http.get<Estudio[]>(this.apiurl + '/estudio')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getEstudiosSinEncuesta(): Observable<Estudio[]>{
    // return this.http.get<Estudio[]>(this.apiurl + '/estudio/estudiosSinEncuesta')
    return this.http.get<Estudio[]>(this.apiurl + '/estudio')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  getEstudio(id): Observable<Estudio[]>{
    return this.http.get<Estudio[]>(this.apiurl + '/estudio/' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getEstudioCliente(id): Observable<Estudio[]>{
    return this.http.get<Estudio[]>(this.apiurl + '/estudio/cliente/' + id)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  // back
  // getEstudioAnalista(user): Observable<Estudio[]>{
  //   return this.http.get<Estudio[]>(this.apiurl + '/estudio/analista/' + user)
  //   .pipe(
  //     retry(1),
  //     catchError(this.handleError)
  //   );
  // }

  // front
  getEstudioAnalista(user): Observable<Estudio[]>{
    return this.http.get<Estudio[]>(this.apiurl + '/estudio')
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getDataMuestra(id): Observable<Encuestado[]>{
    // return this.http.get<Encuestado[]>(this.apiurl + '/estudio/dataMuestra/' + id)
    return this.http.get<Encuestado[]>(this.apiurl + '/encuestado')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  // Front
  // getDataGraficos(idEstudio): Observable<any>{
  //   return this.http.get(this.apiurl + '/graficos')
  //   .pipe(
  //     retry(1),
  //     catchError(this.handleError)
  //   );
  // }

  getDataGraficos(idEstudio): Observable<any>{
    return this.http.get(this.apiurl + '/estudio/resultadoEstudio/' + idEstudio, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  // getEstudioEncuestado(id): Observable<Estudio[]>{
  //   return this.http.get<Estudio[]>(this.apiurl + '/estudio/encuestado/' + id)
  //     .pipe(
  //       retry(1),
  //       catchError(this.handleError)
  //     );
  // }

  getEstudioEncuestado(id): Observable<Estudio[]>{
     return this.http.get<Estudio[]>(this.apiurl + '/estudio/encuestado/' + id)
       .pipe(
         retry(1),
         catchError(this.handleError)
       );
   }

  createEstudio(Estudio): Observable<Estudio[]>{
    return this.http.post<Estudio[]>(this.apiurl + '/estudio', JSON.stringify(Estudio), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  createEstudioCliente(id,Estudio): Observable<Estudio[]>{
    return this.http.post<Estudio[]>(this.apiurl + '/estudio/cliente/' + id, JSON.stringify(Estudio), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updateEstudio(id, Estudio): Observable<Estudio[]>{
    return this.http.put<Estudio[]>(this.apiurl + '/estudio/' + id, JSON.stringify(Estudio), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updateEstudioAdmin(id, Estudio): Observable<Estudio[]>{
    return this.http.put<Estudio[]>(this.apiurl + '/estudio/' + id, JSON.stringify(Estudio), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updateEstudioCliente(id, Estudio): Observable<Estudio[]>{
    return this.http.put<Estudio[]>(this.apiurl + '/estudio/' + id, JSON.stringify(Estudio), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updateEstudioAnalsita(id, Estudio): Observable<Estudio[]>{
    return this.http.put<Estudio[]>(this.apiurl + '/estudio/' + id, JSON.stringify(Estudio), this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  deleteEstudio(id){
    return this.http.delete<Estudio[]>(this.apiurl + '/estudio/' + id, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  aprobarEstudio(idEstudio) {
    console.log('aprobado desde servicio', idEstudio);
    return this.http.post<Estudio[]>(this.apiurl + '/estudio/aprobar/' + idEstudio, this.httpOptions)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  rechazarEstudio(idEstudio) {
    console.log('rechazado desde servicio', idEstudio);
    return this.http.post<Estudio[]>(this.apiurl + '/estudio/rechazar/' + idEstudio, this.httpOptions)
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
