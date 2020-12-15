import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TipoUsuarioService} from '../../services/tipo-usuario.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Usuario} from '../../models/usuario';
import {UsuarioService} from '../../services/usuario.service';

@Component({
  selector: 'app-lista-analista-admin',
  templateUrl: './lista-analista-admin.component.html',
  styleUrls: ['./lista-analista-admin.component.css']
})
export class ListaAnalistaAdminComponent implements OnInit {

  Usuario: Usuario[] = [];
  _id = this.actRoute.snapshot.params._id;
  @Input() usuarioData = {_id: 0, username: '', clave: '', correoElectronico: '', estado: '', tipoUsuario: {_id: 0, descripcion: ''}};

  tipoUsuario: any;
  formUsuario: FormGroup;
  patronCorreoUsuario: any = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

  constructor(
    public tipoUsuarioService: TipoUsuarioService,
    public usuarioService: UsuarioService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder
  ) {
    this.createForm();
  }

  ngOnInit(): void {
    this.loadUsuario();
  }

  loadUsuario(): void{
    this.usuarioService.getUsuarios().subscribe(data => {
      this.Usuario = data;
    });
  }

  deleteUsuario(id){
    this.usuarioService.deleteUsuario(id).subscribe(data => {
      this.loadUsuario();
    });
  }

  updateUsuario(){
    if (this.formUsuario.valid) {
      this.usuarioService.updateUsuario(this.usuarioData._id, this.usuarioData).subscribe(data => {
      });
    }
    else{
      alert('FILL ALL FIELDS');
    }
  }

  editar(usuario){
    this.usuarioData = usuario;
  }

  get usernameUsuario(){
    return this.formUsuario.get('usernameUsuario');
  }

  get claveUsuario(){
    return this.formUsuario.get('claveUsuario');
  }

  get estadoUsuario(){
    return this.formUsuario.get('estadoUsuario');
  }

  get correoElectronicoUsuario(){
    return this.formUsuario.get('correoElectronicoUsuario');
  }

  get descripcionTipoUsuario(){
    return this.formUsuario.get('descripcionTipoUsuario');
  }

  createForm(){
    this.formUsuario = this.formBuilder.group({
      usernameUsuario: ['', Validators.required],
      estadoUsuario: ['',  Validators.required],
      claveUsuario: ['', Validators.required],
      descripcionTipoUsuario: ['', Validators.required],
      correoElectronicoUsuario: ['', [Validators.required, Validators.pattern(this.patronCorreoUsuario)]],
    });
  }

}
