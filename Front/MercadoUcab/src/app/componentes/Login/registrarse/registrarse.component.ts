import {Component, Input, OnInit} from '@angular/core';
import {EncuestadoService} from '../../../services/encuestado.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {GeneroService} from '../../../services/genero.service';
import {OcupacionService} from '../../../services/ocupacion.service';
import {NivelSocioEconomicoService} from '../../../services/nivel-socio-economico.service';
import {LugarService} from '../../../services/lugar.service';
import {Router} from '@angular/router';
import {UsuarioService} from '../../../services/usuario.service';
import {MedioConexionService} from '../../../services/medio-conexion.service';
import {EstadoCivilService} from '../../../services/estado-civil.service';
import {NivelAcademicoService} from '../../../services/nivel-academico.service';

@Component({
  selector: 'app-registrarse',
  templateUrl: './registrarse.component.html',
  styleUrls: ['./registrarse.component.css']
})
export class RegistrarseComponent implements OnInit {

  @Input() encuestado = {id: 0, primerNombre: '', segundoNombre: '' , primerApellido: '', segundoApellido: 0, fechaNacimiento: '', estado: '',
    dtoEstadoCivil: {id: 0, nombre: '', estado: ''},
    dtoNivelAcademico: {id: 0, nombre: '', estado: ''},
    dtoMedioConexion: {id: 0, nombre: '', estado: ''},
    dtoGenero: {id: 0, nombre: '', estado: ''},
    dtoOcupacion: {id: 0, nombre: '', estado: ''},
    dtoNivelSocioEconomico: {id: 0, nombre: '', estado: '', descripcion: ''},
    dtoLugar: {id: 0, estado: '', nombre: '', tipo: ''},
    dtoUsuario: {id: 0, username: '', estado: '', clave: '', correoElectronico: '', dtoTipoUsuario: {id: 0, estado: '', descripcion: ''}},
  };

  estadoCivil: any;
  nivelAcademico: any;
  medioConexion: any;
  genero: any;
  ocupacion: any;
  nivelSocioEconomico: any;
  lugar: any;
  usuario: any;

  estados: any;
  parroquias: any;
  ciudades: any;
  municipios: any;
  auxPaisID: number;
  auxEstadoID: number;
  auxCiudadID: number;
  auxMunicipioID: number;
  auxParroquiaID: number;


  patronFechaNacimientoEncuestado: any = /^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$/;
  patronNombreEncuestado: any = /^[A-Za-z\s]+$/;

  formRegistroEncuestado: FormGroup;

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
    public router: Router,
    private formBuilder: FormBuilder
    ) { this.createForm; }

  ngOnInit(): void {
    this.addEstadoCivil();
    this.addNivelAcademico();
    this.addMedioConexion();
    this.addGenero();
    this.addOcupacion();
    this.addNivelSocioEconomico ();
    this.addLugar();
    this.addUsuario();
  }

  addEncuestado(encuestado){
    if (this.formRegistroEncuestado.valid) {
      this.encuestadoService.createEncuestado(this.encuestado).subscribe((data: {}) => {
      });
    }
    else{
      alert(' LLenar todos los campos por favor');
    }
  }

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

  addLugar(){
    this.lugarService.getLugars().subscribe((Lugares: {}) => {
      this.lugar = Lugares;
    });
  }

  addUsuario(){
    this.usuarioService.getUsuarios().subscribe((Usuarios: {}) => {
      this.usuario = Usuarios;
    });
  }

  busquedaEstado(id){
    // El ID es del pais
    this.auxPaisID = id;
    // Esta peticion se realiza para mostar todos los estados aasociados a ese pais
    this.lugarService.getEstado(id).subscribe((data: {}) => {
      this.estados = data;
    });
  }

  busquedaParroquia(id){
    // El ID es del estado
    this.auxMunicipioID = id;
    // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
    this.lugarService.getParroquia(this.auxPaisID, id).subscribe((data: {}) => {
      this.parroquias = data;
    });
  }

  get primerNombreEncuestado(){return this.formRegistroEncuestado.get('primerNombreEncuestado'); }
  get segundoNombreEncuestado(){return this.formRegistroEncuestado.get('segundoNombreEncuestado'); }
  get primerApellidoEncuestado(){return this.formRegistroEncuestado.get('primerApellidoEncuestado'); }
  get segundoApellidoEncuestado(){return this.formRegistroEncuestado.get('segundoApellidoEncuestado'); }
  get fechaNacimientoEncuestado(){return this.formRegistroEncuestado.get('fechaNacimientoEncuestado'); }
  get estadoEncuestado(){return this.formRegistroEncuestado.get('estadoEncuestado'); }
  get estadoCivilEncuestado(){return this.formRegistroEncuestado.get('estadoCivilEncuestado'); }
  get nivelAcademicoEncuestado(){return this.formRegistroEncuestado.get('nivelAcademicoEncuestado'); }
  get medioConexionEncuestado(){return this.formRegistroEncuestado.get('medioConexionEncuestado'); }
  get generoEncuestado(){return this.formRegistroEncuestado.get('generoEncuestado'); }
  get ocupacionEncuestado(){return this.formRegistroEncuestado.get('ocupacionEncuestado'); }
  get nivelSocioEconomicoEncuestado(){ return this.formRegistroEncuestado.get('nivelSocioEconomicoEncuestado'); }
  get lugarEncuestado(){return this.formRegistroEncuestado.get('lugarEncuestado'); }
  get usuarioEncuestado(){return this.formRegistroEncuestado.get('usuarioEncuestado'); }

  createForm(){
    this.formRegistroEncuestado = this.formBuilder.group({
      primerNombreEncuestado: ['', [Validators.required, Validators.pattern(this.patronNombreEncuestado)]],
      segundoNombreEncuestado: ['', [Validators.required, Validators.pattern(this.patronNombreEncuestado)]],
      primerApellidoEncuestado: ['', [Validators.required, Validators.pattern(this.patronNombreEncuestado)]],
      segundoApellidoEncuestado: ['', [Validators.required, Validators.pattern(this.patronNombreEncuestado)]],
      estadoEncuestado: ['', Validators.required],
      fechaNacimientoEncuestado: ['', [Validators.required, Validators.pattern(this.patronFechaNacimientoEncuestado)]],
      lugarEncuestado: ['', [Validators.required]],
      estadoCivilEncuestado: ['', Validators.required],
      nivelAcademicoEncuestado: ['', Validators.required],
      medioConexionEncuestado: ['', Validators.required],
      generoEncuestado: ['', Validators.required],
      ocupacionEncuestado: ['', Validators.required],
      nivelSocioEconomicoEncuestado: ['', Validators.required],
      usuarioEncuestado: ['', Validators.required],
    });
  }

  // tslint:disable-next-line:typedef
  guardarEncuestado() {
   /* this.encuestadoService.registarEncuestado(this.encuestado)
      .subscribe(
        res => {
          console.log(res);
        },
        err => console.error(err)
      );*/
  }
}
