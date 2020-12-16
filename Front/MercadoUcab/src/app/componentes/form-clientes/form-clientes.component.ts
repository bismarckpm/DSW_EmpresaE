import {Component, Input, KeyValueDiffers, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Lugar} from '../../models/lugar';
import {LugarService} from '../../services/lugar.service';
import {UsuarioService} from '../../services/usuario.service';
import {Router} from '@angular/router';
import {ClienteService} from '../../services/cliente.service';

@Component({
  selector: 'app-form-clientes',
  templateUrl: './form-clientes.component.html',
  styleUrls: ['./form-clientes.component.css']
})
export class FormClientesComponent implements OnInit {

  @Input() cliente = {_id: 0, rif: '', razonSocial: '', estado: '',
    lugar:  {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: '', lugar: {_id: 0, estado: '', nombre: '', tipo: ''}}}},
    usuario: {_id: 0, username: '', estado: '', clave: '', correoElectronico: ''},
    telefono: {_id: 0, numero: ''},

  };

  lugar: any;
  telefono: any;
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
  patronRIFCliente: any = /^[0-9]+$/;

  formCliente: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    public lugarService: LugarService,
    public usuarioService: UsuarioService,
    public clienteService: ClienteService,
    public router: Router) {
    this.createForm();
  }

  ngOnInit(): void {
    this.addLugar();
  }

  addCliente(cliente){
    this.cliente.lugar._id = this.auxParroquiaID;
    if (this.formCliente.valid) {
      this.clienteService.createCliente(this.cliente).subscribe((data: {}) => {
      });
    }
    else{
      alert(' LLenar todos los campos por favor');
    }
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

  get rifCliente() {return this.formCliente.get('rifCliente'); }
  get razonSocialCliente() {return this.formCliente.get('razonSocialCliente'); }
  get estadoCliente() {return this.formCliente.get('estadoCliente'); }
  get lugarCliente() {return this.formCliente.get('lugarCliente'); }
  get telefonoCliente(){return this.formCliente.get('telefonoCliente'); }
  get usernameCliente(){return this.formCliente.get('usernameCliente'); }
  get claveCliente(){return this.formCliente.get('claveCliente'); }
  get correoElectronicoCliente(){return this.formCliente.get('correoElectronicoCliente'); }


  createForm() {
    this.formCliente = this.formBuilder.group({
      rifCliente: ['', [Validators.required, Validators.pattern(this.patronRIFCliente)]],
      razonSocialCliente: ['', Validators.required],
      estadoCliente: ['', Validators.required],
      lugarCliente: ['', Validators.required],
      usernameCliente: ['', Validators.required],
      claveCliente: ['', Validators.required],
      telefonoCliente: ['', [Validators.required, Validators.pattern(this.patronRIFCliente), Validators.maxLength(11)]],
      correoElectronicoCliente: ['', [Validators.required, Validators.pattern(this.patronCorreoCliente)]],
    });
  }
}
