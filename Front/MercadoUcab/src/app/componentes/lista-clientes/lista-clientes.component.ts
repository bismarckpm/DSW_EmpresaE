import {Component, Input, OnInit} from '@angular/core';
import { Cliente } from 'src/app/models/cliente';
import { ClienteService } from 'src/app/services/cliente.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Lugar} from '../../models/lugar';
import {LugarService} from '../../services/lugar.service';
import {ActivatedRoute, Router} from '@angular/router';
import {UsuarioService} from '../../services/usuario.service';
import {ToastService} from "../../services/toast.service";

@Component({
  selector: 'app-lista-clientes',
  templateUrl: './lista-clientes.component.html',
  styleUrls: ['./lista-clientes.component.css']
})
export class ListaClientesComponent implements OnInit {

  aux: any;
  Cliente: Cliente[] = [];
  _id = this.actRoute.snapshot.params._id;
  @Input() clienteData = {_id: 0, rif: '', razonSocial: '', estado: '',
    lugar:  {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: ''}}}},
    usuario: {_id: 0, username: '', estado: '', clave: '', correoElectronico: ''},
  };

  lugar: any;
  usuario: any;
  username: any;
  clave: any;
  correoElectronico: any;
  estados: any;
  parroquias: any;
  municipios: any;
  auxEstadoID: number;
  auxMunicipioID: number;
  auxParroquiaID: number;

  patronCorreoCliente: any = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
  patronUsernameCliente: any = / ^[a-zA-Z0-9]+([._]?[a-zA-Z0-9]+)*$/;
  patronClaveCliente: any = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
  patronRIFCliente: any = /^([VEJPGvejpg]{1})-([0-9]{8})-([0-9]{1}$)/;
  patronTelefonoCliente: any = /^[0-9]+$/;
  formCliente: FormGroup;

  constructor(private formBuilder: FormBuilder,
              public clienteService: ClienteService,
              public lugarService: LugarService,
              public actRoute: ActivatedRoute,
              public router: Router,
              private toast: ToastService,
              public usuarioService: UsuarioService) {this.createForm(); }

  ngOnInit(): void {
    this.addLugar();
    this.loadCliente();
  }


  loadCliente(): void {
    this.clienteService.getClientes().subscribe(data => {
      this.Cliente = data;
    });
  }

  deleteCliente(id){
    this.clienteService.deleteCliente(id).subscribe(data => {
      this.loadCliente();
    });
  }

  updateCliente(){
    this.clienteData.lugar._id = this.auxParroquiaID;
    this.clienteService.updateCliente(this.clienteData._id, this.clienteData).subscribe(data => {
    });
    location.reload();
  }

  editar(cliente){
    //this.addLugar();
    this.clienteData = cliente;
  }

  addLugar(){
    this.lugarService.getLugars().subscribe((Lugares: {}) => {
      this.estados = Lugares;
    });
  }

  busquedaMunicipio(id){
    // El ID es del estado
    this.auxEstadoID = id;
    // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
    if (id > 0 ){
      this.lugarService.getMunicipio(this.auxEstadoID).subscribe((data: {}) => {
        this.municipios = data;
      });
    }
  }

  busquedaParroquia(id){
    // El ID es del estado
    this.auxMunicipioID = id;
    // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
    if (id > 0 ) {
      this.lugarService.getParroquia(this.auxMunicipioID, id).subscribe((data: {}) => {
        this.parroquias = data;
      });
    }
  }

  seleccionarParroquia(id){
    this.auxParroquiaID = id;
  }

  HandleErrorGet(){
    if(this.aux.estado == 'Exitoso'){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje);
      this.Cliente = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje);
    }
  }

  HandleErrorPostPut(){
    if (this.aux.estado == 'Exitoso'){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje);
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje);
    }
  }

  get rifCliente() {return this.formCliente.get('rifCliente'); }
  get razonSocialCliente() {return this.formCliente.get('razonSocialCliente'); }
  get estadoCliente() {return this.formCliente.get('estadoCliente'); }
  get lugarCliente() {return this.formCliente.get('lugarCliente'); }
  get usernameCliente() {return this.formCliente.get('usernameCliente'); }
  get claveCliente() {return this.formCliente.get('claveCliente'); }
  get correoElectronicoCliente() {return this.formCliente.get('correoElectronicoCliente'); }

  createForm() {
    this.formCliente = this.formBuilder.group({
      rifCliente: ['', [Validators.required, Validators.pattern(this.patronRIFCliente)]],
      razonSocialCliente: ['', Validators.required],
      estadoCliente: ['', Validators.required],
      lugarCliente: ['', Validators.required],
      usernameCliente: ['', Validators.required],
      claveCliente: ['', Validators.required],
      correoElectronicoCliente: ['', [Validators.required, Validators.pattern(this.patronCorreoCliente)]]
    });
  }

}
