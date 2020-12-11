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
  
  public user :Usuario[]=[]
  @Input() usuario={username:'', clave:'' };
  formLogin: FormGroup;
 
  constructor(
    public usuarioService:UsuarioService,
    private formBuilder: FormBuilder,
    public router:Router
  ) { this.createForm() }

  ngOnInit(): void {
  }
  


Loggearse(){

    this.usuarioService.getUsuarios().subscribe( data  => {
      console.log(data) ;

      if(data[0].autenticacion ="valido"){
        console.log("==============") ;
        console.log(data[0].username) ;
        console.log(data[0].rol) ; ;
        console.log("==============") ;
              if(data[0].rol='Administrador'){
                this.router.navigate(['/admin/0'])
              }
              if(data[0].rol='Encuestado'){
                this.router.navigate(['/encuestado/0'])
              }
              if(data[0].rol='Analista'){
                this.router.navigate(['/analista/0'])
              }
              if(data[0].rol='Cliente'){
                this.router.navigate(['/cliente/0'])
        }
      }else{
        window.alert("Usuario no registrado o Informacion Incorrecta");
      }
     
   
})  
  /* if (this.formLogin.valid) {
    return this.usuarioService.Logear(this.usuario)
    .subscribe( data  => {
          if(data[0].autenticacion ="valido"){
                  if(data[0].rol='Administradro'){
                    this.router.navigateByUrl('/admin/0')
                  }
                  if(data[0].rol='Encuestado'){
                    this.router.navigateByUrl('/encuestado/0')
                  }
                  if(data[0].rol='Analista'){
                    this.router.navigateByUrl('/analista/0')
                  }
                  if(data[0].rol='Cliente'){
                    this.router.navigateByUrl('/cliente/0')
            }
          }else{
            window.alert("Usuario no registrado o Informacion Incorrecta");
          }
         
       
    })  
}*/

}


logout() {
  // remove user from local storage and set current user to null
  localStorage.removeItem('currentUser');
 // this.currentUserSubject.next(null);
}



  /// Validacion de Datos
  get userName(){return this.formLogin.get('userName');}

  get password(){return this.formLogin.get('password');}

  createForm(){
    this.formLogin = this.formBuilder.group({
      userName: ['',Validators.required] ,
      password: ['', Validators.required],
    });
  }

}
