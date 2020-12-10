import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Analista } from '../models/analista';
import { Subcategoria } from '../models/subcategoria';

@Injectable({
  providedIn: 'root'
})
export class AnalistaService {

  apiurl='http://localhost:3000';

  constructor(private http:HttpClient) { }
// Http Options
  httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

///////// Metodos para ejecutar//////////////
getsubcategorias():Observable<Analista[]>{
  return this.http.get<Subcategoria[]>(this.apiurl+'/analista')
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getsubcategoria(id):Observable<Subcategoria[]>{
  return this.http.get<Subcategoria[]>(this.apiurl+'/analista/'+id)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

createsubcategoria(subcategoria):Observable<Subcategoria[]>{
  return this.http.post<Subcategoria[]>(this.apiurl+'/analista',JSON.stringify(subcategoria), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

updatesubcategoria(id,subcategoria):Observable<Subcategoria[]>{
  return this.http.put<Subcategoria[]>(this.apiurl+'/analista/'+id,JSON.stringify(subcategoria), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

deletesubcategoria(id){
  return this.http.delete<Subcategoria[]>(this.apiurl + '/analista/' + id, this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

///////////////////// Error HandleError
handleError(error) {
  let errorMessage = '';
  if(error.error instanceof ErrorEvent) {
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
