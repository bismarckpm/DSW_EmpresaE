import { Injectable } from '@angular/core';
import { Cliente } from '../models/cliente';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  apiurl='http://localhost:3000';
   
  constructor(private http:HttpClient) { }
// Http Options
  httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
} 

///////// Metodos para ejecutar//////////////
getClientes():Observable<Cliente[]>{
  return this.http.get<Cliente[]>(this.apiurl+'/Cliente')
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getCliente(id):Observable<Cliente[]>{
  return this.http.get<Cliente[]>(this.apiurl+'/Cliente/'+id)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

createCliente(Cliente):Observable<Cliente[]>{
  return this.http.post<Cliente[]>(this.apiurl+'/Cliente',JSON.stringify(Cliente), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

updateCliente(id,Cliente):Observable<Cliente[]>{
  return this.http.put<Cliente[]>(this.apiurl+'/Cliente/'+id,JSON.stringify(Cliente), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

deleteCliente(id){
  return this.http.delete<Cliente[]>(this.apiurl + '/Cliente/' + id, this.httpOptions)
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
