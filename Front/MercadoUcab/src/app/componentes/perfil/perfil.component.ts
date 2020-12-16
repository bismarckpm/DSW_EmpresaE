import {Component, Input, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UsuarioService} from '../../services/usuario.service';
import { Cliente } from 'src/app/models/cliente';
import { Analista } from 'src/app/models/analista';
import { Administrador } from 'src/app/models/Administrador';
import { Encuestado } from 'src/app/models/encuestado';
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

  admin=false;
  analist=false;
  encue=false;
  cli=false;

  @Input() usuario={ _id:0, nuevaClave:"",clave:'' };
  @Input() auxUsuario={nuevaClave:''};

  @Input() Cliente :any
  @Input() Administrador :any
  @Input() Analista :any
  @Input() Encuestado :any
  aux:any;

  
  constructor(

    public usuarioService: UsuarioService,
    public clienteService:ClienteService,
    public administradroService:AdminService,
    public analistaService:AnalistaService,
    public encuestadoservice:EncuestadoService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder
  ) {
  }


  ngOnInit(): void {

    if((JSON.parse(localStorage.getItem("rol") )) == "Administrador"){
        this.administradroService.getAdministradorDelUsuario(parseInt(localStorage.getItem('usuarioID'))).subscribe(data => {
          this.Administrador= data;
        })
        this.admin=true;

    }

    if((JSON.parse(localStorage.getItem("rol") )) == "Cliente"){
      this.clienteService.getClienteDelUsuario(parseInt(localStorage.getItem('usuarioID'))).subscribe(data=>{
        this.Cliente=data;
      })

      this.cli=true;
    }

    if((JSON.parse(localStorage.getItem("rol") )) == "Encuestado"){
      console.log("ENTRO EN LA LLAMADA");
      this.encuestadoservice.getEncuestadoDelUsuario( parseInt(localStorage.getItem('usuarioID'))).subscribe( data => {
        this.Encuestado = data;
        console.log("GUARDO LA INFORMACION");
        console.log(this.Encuestado);
      })
      this.encue=true;  
    }

    if((JSON.parse(localStorage.getItem("rol") )) == "Analista"){
     this.analistaService.getAnalistaDelUsuario(parseInt(localStorage.getItem('usuarioID'))).subscribe(data =>{
       this.Analista =data 
     })

     this.analist=true;

    }
    
  }


  CambioDeClave(){

    this.HacerUsuario();
    this.usuarioService.cambiarclave(this.usuario).subscribe(data=>{
        this.aux = data;
        console.log(this.usuario);
        if(this.aux.Respuesta=="Cambio Satisfactorio"){
          window.alert("Cambio Realizado");
        }else{
          window.alert(" Algo fallo y no soy yo ");
        }
    })

  }




  HacerUsuario(){

    this.usuario._id= parseInt(localStorage.getItem('usuarioID'));
    this.usuario.nuevaClave= this.auxUsuario.nuevaClave;
    console.log(this.usuario);

}

}

