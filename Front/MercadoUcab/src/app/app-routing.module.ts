import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule} from '@angular/router';
import { RegistrarseComponent } from './componentes/login/registrarse/registrarse.component';
import { LoginComponent } from './componentes/login/login.component';
import { RecuperacionComponent } from './componentes/login/recuperacion/recuperacion.component';
import { AdminComponent } from './modulos/admin/admin.component';
import { ClienteComponent } from './modulos/cliente/cliente.component';
import { AnalistaComponent } from './modulos/analista/analista.component';

import { EncuestadoComponent } from './modulos/encuestado/encuestado.component';



const routes: Routes = [
  { path: 'registro', component: RegistrarseComponent},
  { path: 'login', component: LoginComponent},
  { path: 'recuperacion', component: RecuperacionComponent},
  { path: 'admin/:id', component: AdminComponent},
  { path: 'cliente/:id', component: ClienteComponent},
  { path: 'analista/:id', component: AnalistaComponent},
  { path: 'encuestado/:id', component: EncuestadoComponent},
  { path: '**', redirectTo: 'login', pathMatch: 'full'},
];


@NgModule({
  declarations: [],
  imports: [ RouterModule.forRoot(routes)  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
