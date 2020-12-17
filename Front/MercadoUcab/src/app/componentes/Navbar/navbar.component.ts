import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  ruta: string;

bool=true;
  constructor(private router:Router) { 
    console.log(this.router.url.toString().substr(1));
    this.ruta = this.router.url.toString().substr(1);
  }

  ngOnInit(): void {
    if(localStorage.getItem('rol').length == 0){
      this.bool=true;
    }else{
      this.bool=false;
    }
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
    this.bool=true;
   // this.currentUserSubject.next(null);
  }

}
