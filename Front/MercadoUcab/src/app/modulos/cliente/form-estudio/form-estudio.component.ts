import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Encuestado } from 'src/app/models/encuestado';
import { EstudioService } from 'src/app/services/estudio.service';
import { LugarService } from 'src/app/services/lugar.service';
import { NivelSocioEconomicoService } from 'src/app/services/nivel-socio-economico.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { ToastService } from 'src/app/services/toast.service';

class DataMuestra { constructor(public _id: number){} }

@Component({
  selector: 'app-form-estudio-cliente',
  templateUrl: './form-estudio.component.html',
  styleUrls: ['./form-estudio.component.css']
})
export class FormEstudioClienteComponent implements OnInit {

  @Input() estudio = {
    _id: 0,
    nombre: '',
    edadMinima: 0,
    edadMaxima: 0 ,
    fechaInicio: '',
    fechaFin: '',
    genero: '',
    lugar: {
      _id: 0,
      lugar: {
        _id: 0,
        lugar: {
          _id: 0,
          lugar : {_id: 0}
        }
      }
    },
    nivelSocioEconomico: {_id: 0},
    subcategoria : {_id: 0},
    datamuestra: []
  };
  @Input() dataMuestra = {_id : 0};

  nivelSocioEconomico: any;
  subcategoria: any;
  analistas: any;

  // listas para la parte de DataMuestra
  listaMuestraInsertar = [];
  dataMuestraMostrar: Encuestado[] = [];
  listaMuestraSeleccionada = [];

  ///// Atributos para la busqueda de acuerdo a lo seleccionado
  lugar: any;
  parroquias: any;
  estados: any;
  municipios: any;
  auxEstadoID: number;
  auxMunicipioID: number;
  auxParroquiaID: number;

  formEstudio: FormGroup;
  patronEdadEstudio: any = /^(0?[0-9]{1,2}|1[0-7][0-9]|99)$/;
  patronFechaEstudio: any = /^\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$/;
  patronNombreEstudio: any = /^[A-Za-z\s]+$/;

  constructor(
    private formBuilder: FormBuilder,
    public estudioService: EstudioService,
    public lugarService: LugarService,
    public subcategoriaService: SubcategoriaService,
    public nivelsocioeconomicoService: NivelSocioEconomicoService,
    private toast: ToastService
  ) {
    this.createForm();
  }

  ngOnInit(): void {
    this.addLugar();
    this.addNivelSocioEconomico();
    this.addSubcategoria();
  }

  addEstudio(): void {
    console.log(this.estudio.genero);
    if (this.formEstudio.valid) {
      this.estudio.lugar._id = this.auxParroquiaID;
      this.estudio.datamuestra = this.listaMuestraInsertar;
      this.estudioService.createEstudioCliente(JSON.parse(localStorage.getItem('usuarioID')), this.estudio).subscribe((data: {}) => {
      },
      (error) => {
        console.log(error);
        this.toast.showError('Intentelo más tarde', 'Comunicación no disponible');
      }
      );
      this.toast.showSuccess('Su estudio ha sido solicitado con éxito', 'Estudio solicitado');
      location.reload();
    }
    else{
      this.toast.showError('Es necesario llenar los todos los campos', 'Campos incompletos');
    }
  }

  /// Busqueda para los drop de lugar por pais seleccionado previamente
  addLugar(): void{
    this.lugarService.getLugars().subscribe((Lugares: {}) => {
      this.estados = Lugares;
    });
  }

  busquedaMunicipio(id): void{
    // El ID es del estado
    this.auxEstadoID = id;
    // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
    if (id > 0 ){
      this.lugarService.getMunicipio(this.auxEstadoID).subscribe((data: {}) => {
        this.municipios = data;
      });
    }

  }

  busquedaParroquia(id): void{
    // El ID es del estado
    this.auxMunicipioID = id;
    // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
    if (id > 0 ) {
      this.lugarService.getParroquia(this.auxMunicipioID, id).subscribe((data: {}) => {
        this.parroquias = data;
      });
    }
  }

  seleccionarParroquia(id): void{
    this.auxParroquiaID = id;
  }

  // agregar encuestados de la datamuestra a la lista a enviar
  addDataMuestra(): void {
    this.listaMuestraInsertar.push(new DataMuestra(this.dataMuestra._id));
    // aqui tengo que hacer pop de la lista datamuestraMostrar
    this.listaMuestraInsertar.forEach(muestra => {
      this.dataMuestraMostrar.forEach(data => {
        if (data._id === muestra._id) {
          const persona = data.primerNombre + ' ' + data.primerApellido;
          this.listaMuestraSeleccionada.push(persona);
          this.listaMuestraSeleccionada = this.listaMuestraSeleccionada.filter( (value, index, self) => {
            return self.indexOf(value) === index;
          });
        }
      });
    });
    this.dataMuestra._id = 0;
  }

  /////////////// Dropdowns ///////////////////////
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

  // cargarDataMuestra(): void {
  //   this.estudioService.getDataMuestra(/*aqui van los parámetros*/).subscribe(data => {
  //     this.dataMuestraMostrar = data;
  //   });
  //   console.log('datamuestra');
  //   console.log(this.dataMuestraMostrar);
  // }

  //// Validaciones
  get nombreEstudio(){return this.formEstudio.get('nombreEstudio'); }
  get edadMinimaEstudio(){return this.formEstudio.get('edadMinimaEstudio'); }
  get edadMaximaEstudio(){return this.formEstudio.get('edadMaximaEstudio'); }
  get estadoEstudio(){return this.formEstudio.get('estadoEstudio'); }
  get fechaInicioEstudio(){return this.formEstudio.get('fechaInicioEstudio'); }
  get fechaFinEstudio(){return this.formEstudio.get('fechaFinEstudio'); }
  get lugarEstudio(){return this.formEstudio.get('lugarEstudio'); }
  get subcategoriaEstudio(){return this.formEstudio.get('subcategoriaEstudio'); }
  get nivelEstudio(){return this.formEstudio.get('nivelEstudio'); }
  get genero(){return this.formEstudio.get('genero'); }

  createForm(): void {
    this.formEstudio = this.formBuilder.group({
      nombreEstudio: ['', [Validators.required, Validators.pattern(this.patronNombreEstudio)]],
      estadoEstudio: '',
      fechaInicioEstudio: ['', [Validators.required, Validators.pattern(this.patronFechaEstudio)]],
      fechaFinEstudio: '',
      edadMinimaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      edadMaximaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      lugarEstudio: ['', [Validators.required]],
      subcategoriaEstudio: ['', [Validators.required]],
      nivelEstudio: ['', [Validators.required]],
    });
  }

}
