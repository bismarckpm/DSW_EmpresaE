import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TipoUsuarioService} from '../../services/tipo-usuario.service';
import {Router} from '@angular/router';
import {Usuario} from '../../models/usuario';
import {UsuarioService} from '../../services/usuario.service';

@Component({
  selector: 'app-form-analista-admin',
  templateUrl: './form-analista-admin.component.html',
  styleUrls: ['./form-analista-admin.component.css']
})
export class FormAnalistaAdminComponent implements OnInit {

  @Input() usuario = {_id: 0, rol: '', username: '', clave: '', correoElectronico: '', estado: '', tipoUsuario: {_id: 0, descripcion: ''}};
  formUsuario: FormGroup;
  patronCorreoUsuario: any = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

  constructor(
    private formBuilder: FormBuilder,
    public usuarioService: UsuarioService,
    public tipoUsuarioService: TipoUsuarioService,
    public router: Router) { this.createForm(); }

  ngOnInit(): void {
  }

  addUsuario(Usuario){
    if (this.formUsuario.valid){
      this.usuarioService.createUsuario(this.usuario).subscribe((data: {}) => {
      });
    }
    else{
      alert('Llenar todos los campos por favor');
    }
  }

  get usernameUsuario(){
    return this.formUsuario.get('usernameUsuario');
  }

  get estadoUsuario(){
    return this.formUsuario.get('estadoUsuario');
  }

  get claveUsuario(){
    return this.formUsuario.get('claveUsuario');
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
