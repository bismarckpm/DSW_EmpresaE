import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import{ Routes,RouterModule} from'@angular/router';
import { RegistrarseComponent } from './componentes/login/registrarse/registrarse.component';
import { LoginComponent } from './componentes/login/login.component';

const routes: Routes=[
  { path:'registro', component:RegistrarseComponent},
  { path:'login', component:LoginComponent},
  { path:'', redirectTo:'/login',pathMatch:'full'},

];


@NgModule({
  declarations: [],
  imports: [ RouterModule.forRoot(routes)  ],
  exports:[RouterModule]
})
export class AppRoutingModule { }
