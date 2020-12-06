import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() {   
  }

  ngOnInit(): void {
  }

  MandarForm(email,password){
    alert('Enviando');
    console.log(email);
    console.log(password);
  }

  navegarHaciaOlvido(){
    //this.router.navigate(['/Olvido']);
  }

}
