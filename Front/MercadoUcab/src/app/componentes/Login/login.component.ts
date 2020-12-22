import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/models/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user :any;
  @Input() usuario={username:'', clave:'' };
  formLogin: FormGroup;
  userID:any;

  constructor(
    public usuarioService:UsuarioService,
    private formBuilder: FormBuilder,
    public router:Router
  ) { this.createForm() }

  ngOnInit(): void {
  }



  Loggearse(){

  ////////////////////////// Para el proyecto como tal //////////////////////////////////  
   if (this.formLogin.valid) {
      this.usuarioService.Logear(this.usuario).subscribe( data  => {
        this.user=data

        if(this.user.autenticacion=="valida") {
          console.log("==============") ;
          console.log(this.user.username) ;
          console.log(this.user.rol) ; ;
          console.log("==============") ;
          if(this.user.rol=="Administrador"){
            console.log("Entre en  administrador");
            this.router.navigate(['/admin/0']);
            localStorage.setItem('usuarioID',JSON.stringify(this.user._id));
            localStorage.setItem('rol',JSON.stringify(this.user.rol));
          }
          if(this.user.rol=="Encuestado"){
            console.log("Entre en  Encuestado")
            this.router.navigate(['encuestado/0'])
            localStorage.setItem('usuarioID',JSON.stringify(this.user._id));
            localStorage.setItem('rol',JSON.stringify(this.user.rol));
          }
          if(this.user.rol=="Analista"){
            console.log("Entre en  Analsita");
            this.router.navigate(['/analista/0']);
            localStorage.setItem('usuarioID',JSON.stringify(this.user._id));
            localStorage.setItem('rol',JSON.stringify(this.user.rol));
          }
          if(this.user.rol=="Cliente"){
            console.log("Entre en  Cliente");
            this.router.navigate(['/cliente/0']);
            localStorage.setItem('usuarioID',JSON.stringify(this.user._id));
            localStorage.setItem('rol',JSON.stringify(this.user.rol));
          }
        }else {
          window.alert("Usuario no registrado o Informacion Incorrecta");
        }
      });
    }
    ////////////////////////////////Para trabajar en el front Unicamente////////////////

   /* if (this.formLogin.valid) {
      this.usuarioService.getUsuarios().subscribe( data  => {
        console.log(data) ;

        if(data[0].autenticacion=="valida") {
          console.log("==============") ;
          console.log(data[0].username) ;
          console.log(data[0].rol) ; ;
          console.log("==============") ;
          if(data[0].rol=="Administrador"){
            console.log("Entre en  administrador");
            this.router.navigate(['/admin/0']);
            localStorage.setItem('usuarioID',JSON.stringify(data[0]._id));
            localStorage.setItem('rol',JSON.stringify(data[0].rol));
          }
          if(data[0].rol=="Encuestado"){
            console.log("Entre en  Encuestado")
            this.router.navigate(['encuestado/0']);
            localStorage.setItem('usuarioID',JSON.stringify(data[0]._id));
            localStorage.setItem('rol',JSON.stringify(data[0].rol));
          }
          if(data[0].rol=="Analista"){
            console.log("Entre en  Analsita");
            this.router.navigate(['/analista/0']);
            localStorage.setItem('usuarioID',JSON.stringify(data[0]._id));
          localStorage.setItem('rol',JSON.stringify(data[0].rol));
          }
          if(data[0].rol=="Cliente"){
            console.log("Entre en  Cliente");
            this.router.navigate(['/cliente/0']);
            localStorage.setItem('usuarioID',JSON.stringify(data[0]._id));
            localStorage.setItem('rol',JSON.stringify(data[0].rol));
          }
        }else {
          window.alert("Usuario no registrado o Informacion Incorrecta");
        }
      });
    }*/
      

  }



  




logout() {
  // remove user from local storage and set current user to null
  localStorage.removeItem('usuarioID');
  localStorage.removeItem('rol');
  this.router.navigate[('/Login')]
 // this.currentUserSubject.next(null);
}



  /// Validacion de Datos
  get userName(){return this.formLogin.get('userName');}

  get password(){return this.formLogin.get('password');}

  createForm(): void {
    this.formLogin = this.formBuilder.group({
      userName: ['',Validators.required] ,
      password: ['', Validators.required],
    });
  }

}
