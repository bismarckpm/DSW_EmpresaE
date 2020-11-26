// Componentes propios de angular
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { SidebarModule } from 'ng-sidebar';

// Componentes
import { AppComponent } from './app.component';
import { LoginComponent } from './componentes/login/login.component';
import { NavbarComponent } from './componentes/navbar/navbar.component';
import { SidebarComponent } from './componentes/sidebar/sidebar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegistrarseComponent } from './componentes/login/registrarse/registrarse.component';
import { RecuperacionComponent } from './componentes/login/recuperacion/recuperacion.component';
import { ListaComponent } from './componentes/lista/lista.component';
import { FormularioComponent } from './componentes/formulario/formulario.component';
import { AdminComponent } from './modulos/admin/admin.component';
import { ClienteComponent } from './modulos/cliente/cliente.component';
import { UsuarioComponent } from './modulos/usuario/usuario.component';
import { AnalistaComponent } from './modulos/analista/analista.component';


// Services
import { UsuarioService } from './services/usuario.service';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    SidebarComponent,
    RegistrarseComponent,
    RecuperacionComponent,
    ListaComponent,
    FormularioComponent,
    AdminComponent,
    ClienteComponent,
    UsuarioComponent,
    AnalistaComponent
  ],
  imports: [
    BrowserModule,
    SidebarModule.forRoot(),
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,

  ],
  providers: [
    UsuarioService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
