import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError, map } from 'rxjs/operators';
import {Usuario} from '../models/usuario';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private currentUserSubject: BehaviorSubject<Usuario>;
  public currentUser: Observable<Usuario>;


  apiurl = 'http://localhost:3000';

  constructor(private http: HttpClient) { 

    this.currentUserSubject = new BehaviorSubject<Usuario>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }
// Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

///////// Metodos para ejecutar//////////////
  getUsuarios(): Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.apiurl + '/usuario')
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  getUsuario(id): Observable<Usuario[]>{
    return this.http.get<Usuario[]>(this.apiurl + '/usuario/' + id)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  createUsuario(Usuario): Observable<Usuario[]>{
    return this.http.post<Usuario[]>(this.apiurl + '/usuario', JSON.stringify(Usuario), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  updateUsuario(id, Usuario): Observable<Usuario[]>{
    return this.http.put<Usuario[]>(this.apiurl + '/usuario/' + id, JSON.stringify(Usuario), this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  deleteUsuario(id){
    return this.http.delete<Usuario[]>(this.apiurl + '/usuario/' + id, this.httpOptions)
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  Logear(usuario): Observable<Usuario[]>{
      
      return  this.http.post<Usuario[]>(this.apiurl+'/usuario/',JSON.stringify(usuario),this.httpOptions)
      .pipe(map(user => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify(user));
       // this.currentUserSubject.next(user);
        return user;
    }));

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

  handleErrorLogin(error) {
    let errorMessage = '';
    if (error= invali) {
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
