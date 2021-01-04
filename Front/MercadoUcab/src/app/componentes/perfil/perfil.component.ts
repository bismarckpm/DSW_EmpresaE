import {Component, Input, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UsuarioService} from '../../services/usuario.service';

import { ClienteService } from 'src/app/services/cliente.service';
import { AdminService } from 'src/app/services/admin.service';
import { AnalistaService } from 'src/app/services/analista.service';
import { EncuestadoService } from 'src/app/services/encuestado.service';
import {LugarService} from "../../services/lugar.service";
import { EstadoCivilService } from 'src/app/services/estado-civil.service';
import { GeneroService } from 'src/app/services/genero.service';
import { HijoService } from 'src/app/services/hijo.service';
import { MedioConexionService } from 'src/app/services/medio-conexion.service';
import { NivelAcademicoService } from 'src/app/services/nivel-academico.service';
import { NivelSocioEconomicoService } from 'src/app/services/nivel-socio-economico.service';
import { OcupacionService } from 'src/app/services/ocupacion.service';



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
  cambio= false;

  @Input() usuario={ _id:0, nuevaClave:"",clave:'' };
  @Input() auxUsuario={nuevaClave:''};
  @Input() Cliente :any;
  @Input() Administrador :any;
  @Input() Analista :any ;
  @Input() Encuestado :any ;
  aux:any;

  ////////////////////////////Edicion de datos ///////////////////////
  @Input() ClienteData = {_id: 0, rif: '', razonSocial: '', estado: '',
    lugar:  {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: ''}}}},
    usuario: {_id: 0, username: '', estado: '', clave: '', correoelectronico: ''},
    telefono: {_id: 0, numero: ''},
  };


  @Input() UsuarioData = {_id: 0, rol: '', username: '', clave: '', correoelectronico: '', estado: '', tipoUsuario: {_id: 0, descripcion: ''}};


  @Input() EncuestadoData = {_id: 0, primerNombre: '', segundoNombre: '' , primerApellido: '', segundoApellido: 0, fechaNacimiento: '', estado: '',
    estadoCivil: {_id: 0, nombre: '', estado: ''},
    nivelAcademico: {_id: 0, nombre: '', estado: ''},
    medioConexion: {_id: 0, nombre: '', estado: ''},
    genero: {_id: 0, nombre: '', estado: ''},
    ocupacion: {_id: 0, nombre: '', estado: ''},
    nivelSocioEconomico: {_id: 0, nombre: '', estado: '', descripcion: ''},
    telefono: {_id: 0, numero: ''},
    hijo: {_id: 0, fechaNacimientoHijo: '', generoHijo: ''},
    lugar:  {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: ''}}}},
    usuario: {_id: 0, username: '', estado: '', clave: '', correoelectronico: ''},
  };

  @Input() estadoData = {_id: 0, estado: '', nombre: '', tipo: ''}
  @Input() municipioData = {_id: 0, estado: '', nombre: '', tipo: ''}
  @Input() parroquiaData = {_id: 0, estado: '', nombre: '', tipo: ''}


//////////////////////////////////Vergas para los dropdpows//////////
  lugar: any;
  estados: any;
  parroquias: any;
  municipios: any;
  auxEstadoID: number;
  auxMunicipioID: number;
  auxParroquiaID: number;

  estadoCivil: any;
  nivelAcademico: any;
  medioConexion: any;
  genero: any;
  ocupacion: any;
  nivelSocioEconomico: any;
  hijo: any;
  username: any;
  clave: any;
  correoElectronico: any;
  telefono: any;



  /////////////////Vainas de los formularios
  formUsuario:FormGroup;
  formEncuestado:FormGroup;
  formCliente:FormGroup;

////Patrones de Validaciones
  patronCorreoUsuario: any = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;


  patronCorreoCliente: any = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
  patronRIFCliente: any = /^([VEJPGvejpg]{1})-([0-9]{8})-([0-9]{1}$)/;
  patronTelefonoCliente: any = /^[0-9\s]+$/;





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
    public lugarService: LugarService,
    public estadoCivilService: EstadoCivilService,
    public nivelAcademicoService: NivelAcademicoService,
    public medioConexionService: MedioConexionService,
    public generoService: GeneroService,
    public ocupacionService: OcupacionService,
    public nivelSocioEconomicoService: NivelSocioEconomicoService,
    public hijoService: HijoService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder
  ) {
    this.createFormUsuario();
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
      this.encuestadoservice.getEncuestadoDelUsuario( parseInt(localStorage.getItem('usuarioID'))).subscribe( data => {
        this.Encuestado = data; 
      });
      this.encue=true;

    } else if((JSON.parse(localStorage.getItem("rol") )) == "Analista"){
      this.analistaService.getAnalistaDelUsuario(parseInt(localStorage.getItem('usuarioID'))).subscribe(data =>{
        this.Analista =data
      })
      console.log("loqueseas")
      this.analist=true;
    }else{
      console.log('NOOOOOOOOOOOOOOOOOOOOOOOOOOO ENTREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE MALDITA SEAAAAAAAAAAAAAAAAAAAA');
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

  Editar( ){

    
    
    if((JSON.parse(localStorage.getItem("rol") )) == "Administrador"){
      this.UsuarioData=this.Administrador;
    }

    if((JSON.parse(localStorage.getItem("rol") )) == "Cliente"){
      this.ClienteData=this.Cliente;
      this.addLugar()
    }

    if((JSON.parse(localStorage.getItem("rol") )) == "Encuestado"){
      this.EncuestadoData=this.Encuestado;
      this.addEstadoCivil();
      this.addNivelAcademico();
      this.addMedioConexion();
      this.addGenero();
      this.addOcupacion();
      this.addNivelSocioEconomico();
      this.addLugar();
    }

    if((JSON.parse(localStorage.getItem("rol") )) == "Analista"){
      this.UsuarioData=this.Analista;
    }

  }


  HacerUsuario(){

    this.usuario._id = parseInt(localStorage.getItem('usuarioID'));
    this.usuario.nuevaClave = this.auxUsuario.nuevaClave;
    console.log(this.usuario);
  }


  GuardarPerfil(){

    if(this.formUsuario.valid ||  this.formCliente.valid|| this.formEncuestado.valid ){

      if((JSON.parse(localStorage.getItem("rol") )) == "Administrador"){
        this.usuarioService.updateUsuarioPerfil(this.UsuarioData._id,this.UsuarioData)
      }

      if((JSON.parse(localStorage.getItem("rol") )) == "Cliente"){
        this.validarCambiosLugar();
        this.clienteService.updateClientePerfil(this.ClienteData._id,this.ClienteData);
      }

      if((JSON.parse(localStorage.getItem("rol") )) == "Encuestado"){
        console.log("ENTRO EN LA LLAMADA");
        this.encuestadoservice.updateEncuestadoPerfil(this.EncuestadoData._id,this.Encuestado)
      }

      if((JSON.parse(localStorage.getItem("rol") )) == "Analista"){
        this.usuarioService.updateUsuarioPerfil(this.UsuarioData._id,this.UsuarioData)
      }

    }

  }

  Cambio(){
    if(this.cambio == true){
       this.cambio= false;
    }else{
      this.cambio= true;
    }
}

  
validarCambiosLugar(){

  console.log("entro en la llamada de las validaciones de lugar");
  
  if((this.ClienteData.lugar._id != this.parroquiaData._id) && (this.parroquiaData._id == this.auxParroquiaID)){
    console.log("la parroquia es diferente y hago el cambio");
    console.log("id de la parroquia anterior :"+ this.ClienteData.lugar._id);
    this.ClienteData.lugar._id =this.auxParroquiaID;
    console.log("id de la parroquia Actual :"+ this.ClienteData.lugar._id);
  }
  if((this.ClienteData.lugar.lugar._id != this.municipioData._id) && (this.municipioData._id == this.auxMunicipioID)){
    this.ClienteData.lugar.lugar._id =this.auxMunicipioID;
  }

  if((this.ClienteData.lugar.lugar.lugar._id != this.estadoData._id) && (this.estadoData._id == this.auxEstadoID)){
    this.ClienteData.lugar.lugar.lugar._id =this.auxEstadoID;
  }
}

/// Busqueda para los drop de lugar por pais seleccionado previamente
addLugar(){
  this.lugarService.getLugars().subscribe((Lugares: {}) => {
    this.estados = Lugares;
  });
}

addMunicipios(id){
  this.lugarService.getMunicipio(id).subscribe((data: {}) => {
    this.municipios = data;
  });
}

addParroquia(id){
  this.lugarService.getParroquia(0,id).subscribe((data: {}) => {
    this.parroquias = data;
  });
}

/////////////// Llamadas a los drop al haber un cambio ////////////////////////////////////
busquedaMunicipio(id){
  // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
  if (id > 0 ){
      this.auxEstadoID=id         // El ID es del estado
          this.addMunicipios(id)
  }
}

busquedaParroquia(id){
  // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
  if (id > 0 ){     
      this.auxMunicipioID= id;
        this.addParroquia(id)
  }
}

seleccionarParroquia(id){
  this.auxParroquiaID = id;
}

//Drops de los encuestados
addEstadoCivil(){
  this.estadoCivilService.getEstadosCiviles().subscribe((EstadosCiviles: {}) => {
    this.estadoCivil = EstadosCiviles;
  });
}

addNivelAcademico(){
  this.nivelAcademicoService.getNivelesAcademicos().subscribe((NivelesAcademicos: {}) => {
    this.nivelAcademico = NivelesAcademicos;
  });
}

addMedioConexion(){
  this.medioConexionService.getMediosConexion().subscribe((MediosConexion: {}) => {
    this.medioConexion = MediosConexion;
  });
}

addGenero(){
  this.generoService.getGeneros().subscribe((Generos: {}) => {
    this.genero = Generos;
  });
}

addOcupacion(){
  this.ocupacionService.getOcupaciones().subscribe((Ocupaciones: {}) => {
    this.ocupacion = Ocupaciones;
  });
}

addNivelSocioEconomico(){
  this.nivelSocioEconomicoService.getNivelesSocioEconomicos().subscribe((NivelesSocioeconomicos: {}) => {
    this.nivelSocioEconomico = NivelesSocioeconomicos;
  });
}


//Validaciones Cliente
  get rifCliente() {return this.formCliente.get('rifCliente'); }
  get razonSocialCliente() {return this.formCliente.get('razonSocialCliente'); }
  get lugarEstudio(){return this.formCliente.get('lugarEstudio'); }
  get lugarmunicipio(){return this.formCliente.get('lugarmunicipio'); }
  get lugarparroquia(){return this.formCliente.get('lugarparroquia'); }
  get EstadoData(){return this.formCliente.get('EstadoData'); }
  get MunicipioData(){return this.formCliente.get('MunicipioData'); }
  get ParroquiaData(){return this.formCliente.get('ParroquiaData'); }
  get telefonoCliente(){return this.formCliente.get('telefonoCliente'); }
  get usernameCliente(){return this.formCliente.get('usernameCliente'); }
  get claveCliente(){return this.formCliente.get('claveCliente'); }
  get correoElectronicoCliente(){return this.formCliente.get('correoElectronicoCliente'); }


////Validaciones Analista
  get usernameUsuario(){return this.formUsuario.get('usernameUsuario');}
  get estadoUsuario(){return this.formUsuario.get('estadoUsuario');}
  get claveUsuario(){return this.formUsuario.get('claveUsuario');}
  get correoElectronicoUsuario(){return this.formUsuario.get('correoElectronicoUsuario');}
  get descripcionTipoUsuario(){return this.formUsuario.get('descripcionTipoUsuario');}



// Validaciones de encuestado

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
  get lugarmunicipioEncue(){return this.formEncuestado.get('lugarmunicipioEncue'); }
  get lugarparroquiaEncue(){return this.formEncuestado.get('lugarparroquiaEncue'); }
  get EstadoDataEncue(){return this.formEncuestado.get('EstadoDataEncue'); }
  get MunicipioDataEncue(){return this.formEncuestado.get('MunicipioDataEncue'); }
  get ParroquiaDataEncue(){return this.formEncuestado.get('ParroquiaDataEncue'); }

  get usuarioEncuestado(){return this.formEncuestado.get('usuarioEncuestado'); }
  get usernameEncuestado(){return this.formEncuestado.get('usernameEncuestado'); }
  get claveEncuestado(){return this.formEncuestado.get('claveEncuestado'); }
  get correoElectronicoEncuestado(){return this.formEncuestado.get('correoElectronicoEncuestado'); }
  get correoElectronicoAnalista(){return this.formEncuestado.get('correoElectronicoAnalista'); }
  get correoElectronicoAdmin(){return this.formEncuestado.get('correoElectronicoAdmin'); }






  createFormCliente(): void {
    this.formCliente = this.formBuilder.group({
      rifCliente: ['', [Validators.required, Validators.pattern(this.patronRIFCliente)]],
      razonSocialCliente: ['', Validators.required],
      lugarEstudio: [''],
      lugarmunicipio: [''],
      lugarparroquia: [''],
      EstadoData: ['' ],
      MunicipioData: [''],
      ParroquiaData: [''],
      usernameCliente: ['', Validators.required],
      claveCliente: ['', Validators.required],
      telefonoCliente: ['', [Validators.required, Validators.pattern(this.patronRIFCliente), Validators.maxLength(11)]],
      correoElectronicoCliente: ['', [Validators.required, Validators.pattern(this.patronCorreoCliente)]]
    });
  }

  createFormUsuario(): void {
    this.formUsuario = this.formBuilder.group({
      usernameUsuario: ['', Validators.required],
      estadoUsuario: ['',  Validators.required],
      claveUsuario: ['', Validators.required],
      descripcionTipoUsuario: ['', Validators.required],
      correoElectronicoUsuario: ['', [Validators.required, Validators.pattern(this.patronCorreoUsuario)]],
    });
  }

  createFormEncuestado(): void {
    this.formEncuestado = this.formBuilder.group({
      primerNombreEncuestado: ['', [Validators.required, Validators.pattern(this.patrontexto)]],
      segundoNombreEncuestado: ['', [Validators.required, Validators.pattern(this.patrontexto)]],
      primerApellidoEncuestado: ['', [Validators.required, Validators.pattern(this.patrontexto)]],
      segundoApellidoEncuestado: ['', [Validators.required, Validators.pattern(this.patrontexto)]],
      estadoEncuestado: ['', Validators.required],
      fechaNacimientoEncuestado: ['', [Validators.required, Validators.pattern(this.patronFecha)]],
     
      lugarEncuestado: ['' ],
      lugarmunicipioEncue: [''],
      lugarparroquiaEncue: ['' ],
      EstadoDataEncue: [''] ,
      MunicipioDataEncue: [''],
      ParroquiaDataEncue: [''],


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
