import {Component, Input, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UsuarioService} from '../../services/usuario.service';

import { ClienteService } from 'src/app/services/cliente.service';
import { AdminService } from 'src/app/services/admin.service';
import { AnalistaService } from 'src/app/services/analista.service';
import { EncuestadoService } from 'src/app/services/encuestado.service';



@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  admin = false;
  analist = false;
  encue = false;
  cli = false;

  @Input() usuario={ _id:0, nuevaClave:"",clave:'' };
  @Input() auxUsuario={nuevaClave:''};
  @Input() Cliente :any
  @Input() Administrador :any
  @Input() Analista :any
  @Input() Encuestado :any
  aux:any;

  /////////////////Vainas de los formularios
  formAnalista:FormGroup;
  formAdmin:FormGroup;
  formEncuestado:FormGroup;
  formCliente:FormGroup;


  patronFecha: any = /^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$/;
  patrontexto: any = /^[A-Za-z\s]+$/;
  patronCorreo: any = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
  patronTelefonoEncuestado: any = /^[0-9\s]+$/;

  
  constructor(

    public usuarioService: UsuarioService,
    public clienteService: ClienteService,
    public administradroService: AdminService,
    public analistaService: AnalistaService,
    public encuestadoservice: EncuestadoService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder
  ) {  
    this.createFormAdmin();
    this.createFormAnalista();
    this.createFormEncuestado();
    this.createFormCliente();  
  }


  ngOnInit(): void {

    if ((JSON.parse(localStorage.getItem('rol') )) == 'Administrador'){
        this.administradroService.getAdministradorDelUsuario(parseInt(localStorage.getItem('usuarioID'))).subscribe(data => {
          this.Administrador= data;
        })
        console.log("++++++++++++++++++++++++++++++++++ADmin")
        this.admin=true;
    }else if((JSON.parse(localStorage.getItem("rol") )) == "Cliente"){
      this.clienteService.getClienteDelUsuario(parseInt(localStorage.getItem('usuarioID'))).subscribe(data=>{
        this.Cliente=data;
      })
      console.log("++++++++++++++++++++++++++++++++++Cliente")
      this.cli=true;
    }else if((JSON.parse(localStorage.getItem("rol") )) == "Encuestado"){
      console.log("ENTRO EN LA LLAMADA");
      this.encuestadoservice.getEncuestadoDelUsuario( parseInt(localStorage.getItem('usuarioID'))).subscribe( data => {
        this.Encuestado = data;
        console.log('GUARDO LA INFORMACION');
        console.log(this.Encuestado);
      });

      this.encue=true;  
    }if((JSON.parse(localStorage.getItem("rol") )) == "Analista"){
     this.analistaService.getAnalistaDelUsuario(parseInt(localStorage.getItem('usuarioID'))).subscribe(data =>{
       this.Analista =data 
     })
      console.log("loqueseas")
     this.analist=true;
    }else{
      console.log("NOOOOOOOOOOOOOOOOOOOOOOOOOOO ENTREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE MALDITA SEAAAAAAAAAAAAAAAAAAAA")
    }

  }


  CambioDeClave(){
    this.HacerUsuario();
    this.usuarioService.cambiarclave(this.usuario).subscribe(data => {
        this.aux = data;
        console.log(this.usuario);
        if (this.aux.Respuesta == 'Cambio Satisfactorio'){
          window.alert('Cambio Realizado');
        }else{
          window.alert(' Algo fallo y no soy yo ');
        }
    });


  }


  HacerUsuario(){

    this.usuario._id = parseInt(localStorage.getItem('usuarioID'));
    this.usuario.nuevaClave = this.auxUsuario.nuevaClave;
    console.log(this.usuario);
}


GuardarPerfil(){

    if(this.formAdmin.valid || this.formAnalista.valid|| this.formCliente.valid|| this.formEncuestado.valid ){

           if((JSON.parse(localStorage.getItem("rol") )) == "Administrador"){
                this.administradroService.updateAdministrador(this.Administrador._id,this.Administrador)
            }
        
            if((JSON.parse(localStorage.getItem("rol") )) == "Cliente"){
              this.clienteService.updateCliente(this.Cliente._id,this.Cliente);
            }
        
            if((JSON.parse(localStorage.getItem("rol") )) == "Encuestado"){
              console.log("ENTRO EN LA LLAMADA");
              this.encuestadoservice.updateEncuestado(this.Encuestado._id,this.Encuestado)  
            }
        
            if((JSON.parse(localStorage.getItem("rol") )) == "Analista"){
             this.analistaService.updateAnalista(this.Analista._id,this.Analista)
            }
        
    }
      
  }







//Validaciones de encuestado

get primerNombreEncuestado(){return this.formEncuestado.get('primerNombreEncuestado'); }
get segundoNombreEncuestado(){return this.formEncuestado.get('segundoNombreEncuestado'); }
get primerApellidoEncuestado(){return this.formEncuestado.get('primerApellidoEncuestado'); }
get segundoApellidoEncuestado(){return this.formEncuestado.get('segundoApellidoEncuestado'); }
get fechaNacimientoEncuestado(){return this.formEncuestado.get('fechaNacimientoEncuestado'); }
get estadoEncuestado(){return this.formEncuestado.get('estadoEncuestado'); }
get estadoCivilEncuestado(){return this.formEncuestado.get('estadoCivilEncuestado'); }
get nivelAcademicoEncuestado(){return this.formEncuestado.get('nivelAcademicoEncuestado'); }
get medioConexionEncuestado(){return this.formEncuestado.get('medioConexionEncuestado'); }
get generoEncuestado(){return this.formEncuestado.get('generoEncuestado'); }
get ocupacionEncuestado(){return this.formEncuestado.get('ocupacionEncuestado'); }
get nivelSocioEconomicoEncuestado(){ return this.formEncuestado.get('nivelSocioEconomicoEncuestado'); }
get telefonoEncuestado(){ return this.formEncuestado.get('telefonoEncuestado'); }
get fechaNacimientoHijoEncuestado(){ return this.formEncuestado.get('fechaNacimientoHijoEncuestaod'); }
get generoHijoEncuestado(){ return this.formEncuestado.get('generoHijoEncuestado'); }
get lugarEncuestado(){return this.formEncuestado.get('lugarEncuestado'); }
get usuarioEncuestado(){return this.formEncuestado.get('usuarioEncuestado'); }
get usernameEncuestado(){return this.formEncuestado.get('usernameEncuestado'); }
get claveEncuestado(){return this.formEncuestado.get('claveEncuestado'); }
get correoElectronicoEncuestado(){return this.formEncuestado.get('correoElectronicoEncuestado'); }
get correoElectronicoAnalista(){return this.formEncuestado.get('correoElectronicoAnalista'); }
get correoElectronicoCliente(){return this.formEncuestado.get('correoElectronicoCliente'); }
get correoElectronicoAdmin(){return this.formEncuestado.get('correoElectronicoAdmin'); }




 createFormAdmin(): void {
  this.formAdmin = this.formBuilder.group({
    correoElectronicoAdmin: ['', [Validators.required, Validators.pattern(this.patronCorreo)]],

  });
}

createFormCliente(): void {
  this.formCliente = this.formBuilder.group({
    correoElectronicoCliente: ['', [Validators.required, Validators.pattern(this.patronCorreo)]],
  
  });
}

createFormAnalista(): void {
  this.formCliente = this.formBuilder.group({
    correoElectronicoAnalista: ['', [Validators.required, Validators.pattern(this.patronCorreo)]],

  });
}

createFormEncuestado(): void {
  this.formCliente = this.formBuilder.group({
    primerNombreEncuestado: ['', [Validators.required, Validators.pattern(this.patrontexto)]],
    segundoNombreEncuestado: ['', [Validators.required, Validators.pattern(this.patrontexto)]],
    primerApellidoEncuestado: ['', [Validators.required, Validators.pattern(this.patrontexto)]],
    segundoApellidoEncuestado: ['', [Validators.required, Validators.pattern(this.patrontexto)]],
    estadoEncuestado: ['', Validators.required],
    fechaNacimientoEncuestado: ['', [Validators.required, Validators.pattern(this.patronFecha)]],
    lugarEncuestado: ['', Validators.required],
    estadoCivilEncuestado: ['', Validators.required],
    nivelAcademicoEncuestado: ['', Validators.required],
    medioConexionEncuestado: ['', Validators.required],
    generoEncuestado: ['', Validators.required],
    ocupacionEncuestado: ['', Validators.required],
    nivelSocioEconomicoEncuestado: ['', Validators.required],
    fechaNacimientoHijoEncuestado: ['', [Validators.required, Validators.pattern(this.patronFecha)]],
    generoHijoEncuestado: ['', Validators.required],
    telefonoEncuestado: ['', [Validators.required, Validators.pattern(this.patronTelefonoEncuestado), Validators.maxLength(11)]],
    usuarioEncuestado: ['', Validators.required],
    correoElectronicoEncuestado: ['', [Validators.required, Validators.pattern(this.patronCorreo)]],
  

  });
}






}

