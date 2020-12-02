import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subcategoria } from '../models/subcategoria';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SubcategoriaService {
   //Definimos el url del api
   apiurl='http://localhost:3000';
   
  constructor(private http:HttpClient) { }
// Http Options
  httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
} 

///////// Metodos para ejecutar//////////////
getsubcategorias():Observable<Subcategoria>{
  return this.http.get<Subcategoria>(this.apiurl+'/subcategoria')
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getsubcategoria(id):Observable<Subcategoria>{
  return this.http.get<Subcategoria>(this.apiurl+'/subcategoria/'+id)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

createsubcategoria(subcategoria):Observable<Subcategoria>{
  return this.http.post<Subcategoria>(this.apiurl+'/subcategoria',JSON.stringify(subcategoria), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

updatesubcategoria(id,subcategoria):Observable<Subcategoria>{
  return this.http.put<Subcategoria>(this.apiurl+'/subcategoria/'+id,JSON.stringify(subcategoria), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

deletesubcategoria(id){
  return this.http.delete<Subcategoria>(this.apiurl + '/subcategoria/' + id, this.httpOptions)
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
