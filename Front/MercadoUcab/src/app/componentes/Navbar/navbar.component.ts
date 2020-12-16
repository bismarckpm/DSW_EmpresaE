import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  ruta: string;

  constructor(private router:Router) { 
    console.log(this.router.url.toString().substr(1));
    this.ruta = this.router.url.toString().substr(1);
  }

  ngOnInit(): void {
  }

  navegarHaciaRegistro(){
    this.router.navigate(['/registro']);
  }

  navegarHaciaLogin(){
    this.router.navigate(['/login']);
  }

  logout() {
    // remove user from local storage and set current user to null
    localStorage.removeItem('usuarioID');
    localStorage.removeItem('rol');
    this.router.navigate[('/Login')]
   // this.currentUserSubject.next(null);
  }

}
