import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
bool=true;
  constructor(private router:Router) { 
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
    this.router.navigate[('/Login')]
    this.bool=true;
   // this.currentUserSubject.next(null);
  }

}
