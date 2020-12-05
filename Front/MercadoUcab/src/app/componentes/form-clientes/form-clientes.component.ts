import { Component, Input, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UsuarioService} from '../../services/usuario.service';
import {Router} from '@angular/router';
import {ClienteService} from '../../services/cliente.service';

class LugarService {
}

@Component({
  selector: 'app-form-clientes',
  templateUrl: './form-clientes.component.html',
  styleUrls: ['./form-clientes.component.css']
})
export class FormClientesComponent implements OnInit {

  @Input() cliente = { id: 0, estado: '', razonSocial: '', rif: '', dtoLugar: {id: 0}, dtoUsuario: {id: 0}};
  usuario: any;

// Validacion de datos
  formCliente: FormGroup;
  patronRIFCliente: any = /^[0-9]+$/;

  constructor(
    public clienteService: ClienteService,
    public usuarioService: UsuarioService,
    public router: Router,
    private formBuilder: FormBuilder) { this.createForm(); }

  createForm(){
    this.formCliente = this.formBuilder.group({
      rifCliente: ['', [Validators.required, Validators.pattern(this.patronRIFCliente)]],
      razonSocialCliente: ['', Validators.required],
      estadoCliente: ['', Validators.required],
      usuario: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.addusuario();
  }

  addCliente(cliente) {
    if (this.formCliente.valid) {
      this.clienteService.createCliente(this.cliente).subscribe((data: {}) => {
      });
    }
    else{
      alert(' LLenar todos los campos por favor');
    }
  }
// Dropdowns
  addusuario(){
    this.usuarioService.getUsuarios().subscribe((Usuarios: {}) => {
      this.usuario = Usuarios;
    });
  }

  /// Validacion de Datos

  get rifCliente(){
    return this.formCliente.get('rifCliente');
  }

  get razonSocialCliente(){
    return this.formCliente.get('razonSocialCliente');
  }

  get estadoCliente(){
    return this.formCliente.get('estadoCliente');
  }

  get Usuario(){
    return this.formCliente.get('Usuario');
  }

 /* agregarCliente(): void {
    console.log('agrego: ' + this.item.nombre);
    this.item.nombre = '';

  }*/

}
