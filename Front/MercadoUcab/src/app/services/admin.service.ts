import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Administrador } from '../models/Administrador';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  secciones: string[] = [
    'Inicio',
    'Clientes',
    'Encuestados',
    'Administradors',
    'Estudios',
    'Encuestas',
    'Preguntas',
    'Categorias',
    'Subcategorias',
    'Marcas',
    'Presentaciones',
    'Tipos'
  ];
  
  apiurl='http://localhost:3000';
  //apiurl = 'http://localhost:8080/servicio-1.0-SNAPSHOT/api';

  constructor(
    private http: HttpClient
  ) { console.log('servicio ADMIN'); }



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
getAdministradors():Observable<Administrador[]>{
  return this.http.get<Administrador[]>(this.apiurl+'/Administrador')
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getAdministrador(id):Observable<Administrador[]>{
  return this.http.get<Administrador[]>(this.apiurl+'/Administrador/'+id)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

getAdministradorDelUsuario(Administrador):Observable<Administrador[]>{
  return this.http.get<Administrador[]>(this.apiurl+'/usuario/'+Administrador)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

createAdministrador(Administrador):Observable<Administrador[]>{
  return this.http.post<Administrador[]>(this.apiurl+'/Administrador',JSON.stringify(Administrador), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

updateAdministrador(id,Administrador):Observable<Administrador[]>{
  return this.http.put<Administrador[]>(this.apiurl+'/Administrador/'+id,JSON.stringify(Administrador), this.httpOptions)
  .pipe(
    retry(1),
    catchError(this.handleError)
  )
}

deleteAdministrador(id){
  return this.http.delete<Administrador[]>(this.apiurl + '/Administrador/' + id, this.httpOptions)
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
