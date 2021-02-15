import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Cliente } from 'src/app/models/cliente';
import { Estudio } from 'src/app/models/estudio';

import { EstudioService } from 'src/app/services/estudio.service';
import { LugarService } from 'src/app/services/lugar.service';
import { NivelSocioEconomicoService } from 'src/app/services/nivel-socio-economico.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import {ToastService} from "../../../services/toast.service";

@Component({
  selector: 'app-lista-estudio-cliente',
  templateUrl: './lista-estudio.component.html',
  styleUrls: ['./lista-estudio.component.css']
})
export class ListaEstudioClienteComponent implements OnInit {

  cambio = false;
  // Declaracion de variables
  aux: any;
  estudios: Estudio[] = [];
  _id = this.actRoute.snapshot.params['_id'];
  @Input() estudioData = {_id: 0,
    nombre: '',
    estado: 'Solicitado',
    comentarioAnalista : '',
    edadMinima: 0,
    edadMaxima: 0 ,
    fechaInicio: '',
    fechaFin: '',
    genero: '',
    lugar : {
      _id: 0,
      estado: '',
      nombre: '',
      tipo: '',
      lugar : {
        _id: 0,
        estado: '',
        nombre: '',
        tipo: '',
        lugar : {
          _id: 0,
          estado: '',
          nombre: '',
          tipo: ''
        }
      }
    },
    nivelSocioEconomico: {_id: 0, nombre: '', estado: '', descripcion: ''},
    subcategoria : {_id: 0, nombre: '', estado: ''},
  };

  @Input() estadoData = {_id: 0, estado: '', nombre: '', tipo: ''};
  @Input() municipioData = {_id: 0, estado: '', nombre: '', tipo: ''};
  @Input() parroquiaData = {_id: 0, estado: '', nombre: '', tipo: ''};

  // Declaracion para los dropdown
  nivelSocioEconomico: any;
  subcategoria: any;

  ///// Atributos para la busqueda de acuerdo a lo seleccionado
  lugar: any;
  parroquias: any;
  estados: any;
  municipios: any;
  auxEstadoID: number;
  auxMunicipioID: number;
  auxParroquiaID: number;

  // Declaracion para validar
  formEstudio: FormGroup;
  patronEdadEstudio: any = /^(0?[0-9]{1,2}|1[0-7][0-9]|99)$/;
  patronFechaEstudio: any = /^\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$/;
  patronNombreEstudio: any = /^[A-Za-z\s]+$/;

  constructor(
  public estudioService: EstudioService,
  public subcategoriaService: SubcategoriaService,
  public lugarService: LugarService,
  public nivelsocioeconomicoService: NivelSocioEconomicoService,
  public actRoute: ActivatedRoute,
  public router: Router,
  private toast: ToastService,
  private formBuilder: FormBuilder
  ) {this.createForm(); }

  ngOnInit(): void {
   this.loadEstudios();
  }

  loadEstudios(): void {
    localStorage.setItem('usuarioId', JSON.stringify('1'));
    const id = JSON.parse(localStorage.getItem('usuarioID'));

    this.estudioService.getEstudioCliente(id).subscribe(data => {
      this.estudios = data;
    });
  }

  deleteEstudio(id): void {
    this.estudioService.deleteEstudio(id).subscribe(data => {
      this.loadEstudios();
    });
    location.reload();
  }

  Cambio(): void{
    if (this.cambio === true){
       this.cambio = false;
    }else{
      this.cambio = true;
    }
  }

  updateEstudio(){
    console.log('entro a update');

    if (this.formEstudio.valid) {
      console.log('la vaidaciones son correctas');

      if (this.cambio === true){
        console.log('estado actual del cambio :' + this.cambio);
        console.log('llamo validar los cambios del lugar');
        this.validarCambiosLugar();
        console.log('Hago la llamada de http con este estudio :');
        console.log(this.estudioData);
        this.estudioService.updateEstudioCliente(this.estudioData._id, this.estudioData).subscribe(data => {
          });
        console.log('termine la llamada del http');
        this.cambio = false;
        console.log('estado actual del cambio :' + this.cambio);
      }else if (this.cambio == false){
        console.log('estado actual del cambio :' + this.cambio);
        this.estudioService.updateEstudioCliente(this.estudioData._id, this.estudioData).subscribe(data => {
        });
        this.loadEstudios();
      }
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
    location.reload();
  }

  editar(estudio): void{
    this.addSubcategoria();
    this.addLugar();
    this.addNivelSocioEconomico();
    this.estudioData = estudio;
  }

  /// Busqueda para los drop de lugar por pais seleccionado previamente
  addLugar(): void{
    this.lugarService.getLugars().subscribe((Lugares: {}) => {
      this.estados = Lugares;
    });
  }

  addMunicipios(id): void{
    this.lugarService.getMunicipio(id).subscribe((data: {}) => {
      this.municipios = data;
    });
  }

  addParroquia(id): void{
    this.lugarService.getParroquia(0, id).subscribe((data: {}) => {
      this.parroquias = data;
    });
  }

  /////////////// Llamadas a los drop al haber un cambio ////////////////////////////////////
  busquedaMunicipio(id): void{
    // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
    if (id > 0 ){
        this.auxEstadoID = id         // El ID es del estado
        this.addMunicipios(id)
    }
  }

  busquedaParroquia(id): void{
    // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
    if (id > 0 ){
      this.auxMunicipioID = id;
      this.addParroquia(id);
    }
  }

  seleccionarParroquia(id): void{
    this.auxParroquiaID = id;
  }


  /// Esto es para mostrar en los drops doww
  addSubcategoria(): void{
    this.subcategoriaService.getsubcategorias().subscribe((data: {}) => {
      this.subcategoria = data;
    });
  }

  addNivelSocioEconomico(): void{
    this.nivelsocioeconomicoService.getNivelesSocioEconomicos().subscribe((data: {}) => {
      this.nivelSocioEconomico = data;
    });
  }

  validarCambiosLugar(){
    console.log('entro en la llamada de las validaciones de lugar');

    if ((this.estudioData.lugar._id != this.parroquiaData._id) && (this.parroquiaData._id == this.auxParroquiaID)){
      console.log('la parroquia es diferente y hago el cambio');
      console.log('id de la parroquia anterior :' + this.estudioData.lugar._id);
      this.estudioData.lugar._id = this.auxParroquiaID;
      console.log('id de la parroquia Actual :' + this.estudioData.lugar._id);
    }

    if ((this.estudioData.lugar.lugar._id != this.municipioData._id) && (this.municipioData._id == this.auxMunicipioID)){
      this.estudioData.lugar.lugar._id = this.auxMunicipioID;
    }

    if ((this.estudioData.lugar.lugar.lugar._id != this.estadoData._id) && (this.estadoData._id == this.auxEstadoID)){
      this.estudioData.lugar.lugar.lugar._id = this.auxEstadoID;
    }

  }

  HandleErrorGet(){
    if (this.aux.estado == 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
      this.estudios = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }

  HandleErrorPostPut(){
    if (this.aux.estado == 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }


  // Validaciones de Pregunta
  get nombreEstudio(){return this.formEstudio.get('nombreEstudio'); }
  get comentarioAnalistaEstudio(){return this.formEstudio.get('comentarioAnalistaEstudio'); }
  get edadMinimaEstudio(){return this.formEstudio.get('edadMinimaEstudio'); }
  get edadMaximaEstudio(){return this.formEstudio.get('edadMaximaEstudio'); }
  get fechaInicioEstudio(){return this.formEstudio.get('fechaInicioEstudio'); }
  get lugarEstudio(){return this.formEstudio.get('lugarEstudio'); }
  get lugarmunicipio(){return this.formEstudio.get('lugarmunicipio'); }
  get lugarparroquia(){return this.formEstudio.get('lugarparroquia'); }
  get EstadoData(){return this.formEstudio.get('EstadoData'); }
  get MunicipioData(){return this.formEstudio.get('MunicipioData'); }
  get ParroquiaData(){return this.formEstudio.get('ParroquiaData'); }
  get subcategoriaEstudio(){return this.formEstudio.get('subcategoriaEstudio'); }
  get nivelEstudio(){return this.formEstudio.get('nivelEstudio'); }

  createForm(): void {
    this.formEstudio = this.formBuilder.group({
      nombreEstudio: ['', [Validators.required, Validators.pattern(this.patronNombreEstudio)]],
      lugarEstudio: [''],
      lugarmunicipio: [''],
      lugarparroquia: [''],
      EstadoData: ['' ],
      MunicipioData: [''],
      ParroquiaData: [''],
      fechaInicioEstudio: ['', [Validators.required, Validators.pattern(this.patronFechaEstudio)]],
      edadMinimaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      edadMaximaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      comentarioAnalistaEstudio: ['', Validators.pattern(this.patronNombreEstudio)],
      subcategoriaEstudio: ['', [Validators.required]],
      nivelEstudio: [''],
    });
  }

}
