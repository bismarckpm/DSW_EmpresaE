// Componentes propios de angular
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
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
import { AdminComponent } from './modulos/admin/admin.component';
import { ClienteComponent } from './modulos/cliente/cliente.component';
import { UsuarioComponent } from './modulos/usuario/usuario.component';
import { AnalistaComponent } from './modulos/analista/analista.component';
import { FomrAnalistaComponent } from './componentes/fomr-analista/fomr-analista.component';
import { ListaAnalistaComponent } from './componentes/lista-analista/lista-analista.component';
import { CambiarClaveComponent } from './componentes/cambiar-clave/cambiar-clave.component';
import { EditClienteComponent } from './componentes/edit-cliente/edit-cliente.component';
import { EditUsuarioComponent } from './componentes/edit-usuario/edit-usuario.component';
import { FormTipoComponent } from './componentes/form-tipo/form-tipo.component';
import { ListaTipoComponent } from './componentes/lista-tipo/lista-tipo.component';
import { FormEstudioComponent } from './componentes/form-estudio/form-estudio.component';
import {FormSubcategoriaComponent} from './componentes/form-subcategoria/form-subcategoria.component';
import {FormClientesComponent} from './componentes/form-clientes/form-clientes.component';
import {FormMarcaComponent} from './componentes/form-marca/form-marca.component';
import {ListaMarcaComponent} from './componentes/lista-marca/lista-marca.component';
import {ListaPreguntasComponent} from './componentes/lista-preguntas/lista-preguntas.component';
import {MainAdminComponent} from './componentes/main-admin/main-admin.component';
import {ListaEstudiosComponent} from './componentes/lista-estudios/lista-estudios.component';
import {FormCategoriaComponent} from './componentes/form-categoria/form-categoria.component';
import {ListaCategoriaComponent} from './componentes/lista-categoria/lista-categoria.component';
import {FormUsuariosComponent} from './componentes/form-usuarios/form-usuarios.component';
import {ListaSubcategoriaComponent} from './componentes/lista-subcategoria/lista-subcategoria.component';
import {ListaClientesComponent} from './componentes/lista-clientes/lista-clientes.component';
import {FormPresentacionComponent} from './componentes/form-presentacion/form-presentacion.component';
import {ListaPresentacionComponent} from './componentes/lista-presentacion/lista-presentacion.component';
import {FormPreguntaComponent} from './componentes/form-pregunta/form-pregunta.component';
import { FormEncuestaComponent } from './componentes/form-encuesta/form-encuesta.component';
import { ListaEncuestaComponent } from './componentes/lista-encuesta/lista-encuesta.component';



// Services
import { UsuarioService } from './services/usuario.service';
import { AdminService } from './services/admin.service';
import { MarcaService } from './services/marca.service';
import { SubcategoriaService } from './services/subcategoria.service';
import { CategoriaService } from './services/categoria.service';
import { EncuestaService } from './services/encuesta.service';
import { TipoService } from './services/tipo.service';
import { TipoPregunta } from './models/tipo-pregunta';
import { TipoPreguntaService } from './services/tipo-pregunta.service';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    SidebarComponent,
    RegistrarseComponent,
    RecuperacionComponent,
    ListaComponent,
    AdminComponent,
    ClienteComponent,
    UsuarioComponent,
    AnalistaComponent,
    FormClientesComponent,
    FormMarcaComponent,
    ListaMarcaComponent,
    ListaPreguntasComponent,
    MainAdminComponent,
    ListaEstudiosComponent,
    ListaCategoriaComponent,
    FormCategoriaComponent,
    FormUsuariosComponent,
    ListaSubcategoriaComponent,
    FormSubcategoriaComponent,
    FormTipoComponent,
    ListaTipoComponent,
    FormEstudioComponent,
    ListaEstudiosComponent,
    FormClientesComponent,
    ListaClientesComponent,
    FormPresentacionComponent,
    ListaPresentacionComponent,
    FomrAnalistaComponent,
    ListaAnalistaComponent,
    CambiarClaveComponent,
    EditClienteComponent,
    EditUsuarioComponent,
    FormPreguntaComponent,
    FormEncuestaComponent,
    ListaEncuestaComponent
  ],
  imports: [
    BrowserModule,
    SidebarModule.forRoot(),
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,

  ],
  providers: [
    UsuarioService,
    AdminService,
    MarcaService,
    SubcategoriaService,
    CategoriaService,
    EncuestaService,
    TipoService,
    TipoPreguntaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
