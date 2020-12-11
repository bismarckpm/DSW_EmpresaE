import {Component, Input, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Encuestado} from '../../models/encuestado';
import {EncuestadoService} from '../../services/encuestado.service';
import {EstadoCivilService} from '../../services/estado-civil.service';
import {NivelAcademicoService} from '../../services/nivel-academico.service';
import {MedioConexionService} from '../../services/medio-conexion.service';
import {GeneroService} from '../../services/genero.service';
import {OcupacionService} from '../../services/ocupacion.service';
import {NivelSocioEconomicoService} from '../../services/nivel-socio-economico.service';
import {LugarService} from '../../services/lugar.service';
import {UsuarioService} from '../../services/usuario.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  Encuestado: Encuestado[] = [];
  _id = this.actRoute.snapshot.params['_id'];
  @Input() encuestadoData = {_id: 0, primerNombre: '', segundoNombre: '' , primerApellido: '', segundoApellido: 0, fechaNacimiento: '', estado: '',
    estadoCivil: {_id: 0, nombre: '', estado: ''},
    nivelAcademico: {_id: 0, nombre: '', estado: ''},
    medioConexion: {_id: 0, nombre: '', estado: ''},
    genero: {_id: 0, nombre: '', estado: ''},
    ocupacion: {_id: 0, nombre: '', estado: ''},
    nivelSocioEconomico: {_id: 0, nombre: '', estado: '', descripcion: ''},
    lugar: {_id: 0, estado: '', nombre: '', tipo: ''},
    usuario: {_id: 0, username: '', estado: '', clave: '', correoElectronico: ''},
  };

  encuestado: any;
  estadoCivil: any;
  nivelAcademico: any;
  medioConexion: any;
  genero: any;
  ocupacion: any;
  nivelSocioEconomico: any;
  lugar: any;
  usuario: any;

  estados: any;
  municipios: any;
  parroquias: any;
  auxEstadoID: number;
  auxMunicipioID: number;

  formActualizarEncuestado: FormGroup;
  patronFechaNacimientoEncuestado: any = /^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$/;
  patronNombreEncuestado: any = /^[A-Za-z\s]+$/;
  patronCorreoEncuestado: any = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

  // tslint:disable-next-line:variable-name
  constructor(
    public encuestadoService: EncuestadoService,
    public estadoCivilService: EstadoCivilService,
    public nivelAcademicoService: NivelAcademicoService,
    public medioConexionService: MedioConexionService,
    public generoService: GeneroService,
    public ocupacionService: OcupacionService,
    public nivelSocioEconomicoService: NivelSocioEconomicoService,
    public lugarService: LugarService,
    public usuarioService: UsuarioService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder
  ) {
    this.createForm();
  }


  ngOnInit(): void {
    this.loadEncuestado();
  }

  loadEncuestado(): void {
    this.encuestadoService.getEncuestado(this._id).subscribe(data => {
      this.Encuestado = data;
    });
  }

  updateEncuestado(){
    if (this.formActualizarEncuestado.valid) {
      this.encuestadoService.updateEncuestado(this.encuestadoData._id, this.encuestadoData).subscribe(data => {
      });
      this.loadEncuestado();
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
  }

  editar(encuestado){
    this.encuestadoData = encuestado;
    this.addEstadoCivil();
    this.addNivelSocioEconomico();
    this.addGenero();
    this.addNivelAcademico();
    this.addOcupacion();
    this.addMedioConexion();
    this.addLugar();
  }

  addLugar(){
    this.lugarService.getLugars().subscribe((data: {}) => {
      this.lugar = data;
    });
  }

  addEstadoCivil(){
    this.estadoCivilService.getEstadosCiviles().subscribe((data: {}) => {
      this.estadoCivil = data;
    });
  }

  addNivelSocioEconomico(){
    this.nivelSocioEconomicoService.getNivelesSocioEconomicos().subscribe((data: {}) => {
      this.nivelSocioEconomico = data;
    });
  }

  addGenero(){
    this.generoService.getGeneros().subscribe((data: {}) => {
      this.genero = data;
    });
  }

  addNivelAcademico(){
    this.nivelAcademicoService.getNivelesAcademicos().subscribe((data: {}) => {
      this.nivelAcademico = data;
    });
  }

  addOcupacion(){
    this.ocupacionService.getOcupaciones().subscribe((data: {}) => {
      this.ocupacion = data;
    });
  }

  addMedioConexion(){
    this.medioConexionService.getMediosConexion().subscribe((data: {}) => {
      this.medioConexion = data;
    });
  }
///// Busqueda lugar
  busquedaMunicipio(id){
    // El ID es del estado
    this.auxEstadoID = id;
    // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
    this.lugarService.getMunicipio(id).subscribe((data: {}) => {
      this.municipios = data;
    });
  }


  busquedaParroquia(id){
    // El ID es del estado
    this.auxMunicipioID = id;
    // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
    this.lugarService.getParroquia(this.auxMunicipioID, id).subscribe((data: {}) => {
      this.parroquias = data;
    });
  }

  /// Validacion de Datos

  get primerNombreEncuestado(){return this.formActualizarEncuestado.get('primerNombreEncuestado'); }
  get segundoNombreEncuestado(){return this.formActualizarEncuestado.get('segundoNombreEncuestado'); }
  get primerApellidoEncuestado(){return this.formActualizarEncuestado.get('primerApellidoEncuestado'); }
  get segundoApellidoEncuestado(){return this.formActualizarEncuestado.get('segundoApellidoEncuestado'); }
  get fechaNacimientoEncuestado(){return this.formActualizarEncuestado.get('fechaNacimientoEncuestado'); }
  get estadoEncuestado(){return this.formActualizarEncuestado.get('estadoEncuestado'); }
  get estadoCivilEncuestado(){return this.formActualizarEncuestado.get('estadoCivilEncuestado'); }
  get nivelAcademicoEncuestado(){return this.formActualizarEncuestado.get('nivelAcademicoEncuestado'); }
  get medioConexionEncuestado(){return this.formActualizarEncuestado.get('medioConexionEncuestado'); }
  get generoEncuestado(){return this.formActualizarEncuestado.get('generoEncuestado'); }
  get ocupacionEncuestado(){return this.formActualizarEncuestado.get('ocupacionEncuestado'); }
  get nivelSocioEconomicoEncuestado(){ return this.formActualizarEncuestado.get('nivelSocioEconomicoEncuestado'); }
  get lugarEncuestado(){return this.formActualizarEncuestado.get('lugarEncuestado'); }
  get usuarioEncuestado(){return this.formActualizarEncuestado.get('usuarioEncuestado'); }
  get usernameEncuestado(){return this.formActualizarEncuestado.get('usernameEncuestado'); }
  get claveEncuestado(){return this.formActualizarEncuestado.get('claveEncuestado'); }
  get correoElectronicoEncuestado(){return this.formActualizarEncuestado.get('correoElectronicoEncuestado'); }

  createForm(){
    this.formActualizarEncuestado = this.formBuilder.group({
      primerNombreEncuestado: ['', [Validators.required, Validators.pattern(this.patronNombreEncuestado)]],
      segundoNombreEncuestado: ['', [Validators.required, Validators.pattern(this.patronNombreEncuestado)]],
      primerApellidoEncuestado: ['', [Validators.required, Validators.pattern(this.patronNombreEncuestado)]],
      segundoApellidoEncuestado: ['', [Validators.required, Validators.pattern(this.patronNombreEncuestado)]],
      estadoEncuestado: ['', Validators.required],
      fechaNacimientoEncuestado: ['', [Validators.required, Validators.pattern(this.patronFechaNacimientoEncuestado)]],
      lugarEncuestado: ['', Validators.required],
      estadoCivilEncuestado: ['', Validators.required],
      nivelAcademicoEncuestado: ['', Validators.required],
      medioConexionEncuestado: ['', Validators.required],
      generoEncuestado: ['', Validators.required],
      ocupacionEncuestado: ['', Validators.required],
      nivelSocioEconomicoEncuestado: ['', Validators.required],
      usuarioEncuestado: ['', Validators.required],
      usernameEncuestado: ['', Validators.required],
      claveEncuestado: ['', Validators.required],
      correoElectronicoEncuestado: ['', [Validators.required, Validators.pattern(this.patronCorreoEncuestado)]],
    });
  }
}

