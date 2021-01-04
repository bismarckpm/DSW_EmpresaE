import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Analista } from '../models/analista';


@Injectable({
  providedIn: 'root'
})
export class AnalistaService {

  secciones: string[] = [
    'Perfil',
    'Estudios',
  ];

 //apiurl='http://localhost:3000';
  apiurl='http://localhost:8080/servicio-1.0-SNAPSHOT/api';

  constructor(private http: HttpClient) { }
// Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
  })
};

////////////// Sidbard////////////


getSecciones(): string[] {
  return this.secciones;
}

getSeccion(i): string {
  return this.secciones[i];
}



///////// Metodos para ejecutar//////////////
getAnalistas():Observable<Analista[]>{
  return this.http.get<Analista[]>(this.apiurl+'/analista')
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getAnalistaDelUsuario(Analista):Observable<Analista[]>{
  return this.http.get<Analista[]>(this.apiurl+'/usuario/'+Analista)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getAnalista(id):Observable<Analista[]>{
  return this.http.get<Analista[]>(this.apiurl+'/analista/'+id)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

createAnalista(Analista):Observable<Analista[]>{
  return this.http.post<Analista[]>(this.apiurl+'/analista',JSON.stringify(Analista), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

updateAnalista(id,Analista):Observable<Analista[]>{
  return this.http.put<Analista[]>(this.apiurl+'/analista/'+id,JSON.stringify(Analista), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

deleteAnalista(id){
  return this.http.delete<Analista[]>(this.apiurl + '/analista/' + id, this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
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
