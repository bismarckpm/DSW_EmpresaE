import {ChangeDetectorRef, Component, Input, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Estudio } from 'src/app/models/estudio';
import { Lugar } from 'src/app/models/Lugar';
import { NivelSocioEconomico } from 'src/app/models/nivel-socio-economico';
import { Subcategoria } from 'src/app/models/subcategoria';
import { Encuestado } from 'src/app/models/encuestado';
import { AnalistaService } from 'src/app/services/analista.service';
import { CategoriaService } from 'src/app/services/categoria.service';
import { EncuestaService } from 'src/app/services/encuesta.service';
import { EstudioService } from 'src/app/services/estudio.service';
import { GeneroService } from 'src/app/services/genero.service';
import { LugarService } from 'src/app/services/lugar.service';
import { NivelSocioEconomicoService } from 'src/app/services/nivel-socio-economico.service';
import { PreguntaService } from 'src/app/services/pregunta.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { ToastService } from 'src/app/services/toast.service';

class DataMuestra { constructor(public _id: number){} }

@Component({
  selector: 'app-lista-estudios',
  templateUrl: './lista-estudios.component.html',
  styleUrls: ['./lista-estudios.component.css']
})
export class ListaEstudiosComponent implements OnInit {
  cambio = false;

  // Declaracion de variables
  estudios: Estudio[] = [];
  _id = this.actRoute.snapshot.params._id;
  @Input() estudioData = {_id: 0, nombre: '', estado:'', comentarioAnalista : '', edadMinima: 0, edadMaxima: 0 , fechaInicio: '', fechaFin: '',
             lugar : {_id: 0, estado: '', nombre: '', tipo: '', lugar : {_id: 0, estado: '', nombre: '', tipo: '', lugar : {_id: 0, estado: '', nombre: '', tipo: ''}}},
             nivelSocioEconomico: {_id: 0, nombre: '', estado: '', descripcion: ''},
             subcategoria : {_id: 0, nombre: '', estado: '',categoria:{_id:0}},
             analista: {_id: 0},
             genero:{_id:0},

            };

   @Input() estadoData = {_id: 0, estado: '', nombre: '', tipo: ''}
   @Input() municipioData = {_id: 0, estado: '', nombre: '', tipo: ''}
   @Input() parroquiaData = {_id: 0, estado: '', nombre: '', tipo: ''}

  // Declaracion para los dropdown
  nivelSocioEconomico: any;
  subcategoria: any;
  analistas: any;
  genero: any;
  categorias: any;

  ///// Atributos para la busqueda de acuerdo a lo seleccionado
  lugar: any;
  parroquias: any;
  estados: any;
  municipios: any;
  auxEstadoID: number;
  auxMunicipioID: number;
  auxParroquiaID: number;
  aux: any = [];
  // Declaracion para validar
  formEstudio: FormGroup;
  patronEdadEstudio: any = /^(0?[0-9]{1,2}|1[0-7][0-9]|99)$/;
  patronFechaEstudio: any = /^\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$/;
  patronNombreEstudio: any = /^[A-Za-z\s]+$/;

  ////// Para encuesta
  /// PAra validar ENCUESTA
  formEncuesta: FormGroup;

  @Input() encuesta = {
    _id: 0,
    estado: 'Activo',
    fechaInicio: '',
    fechaFin: '',
    estudio: {_id: 0},
    preguntas: [],
    encuestados: []
  };
  @Input() preguntaEstudio = { };
  @Input() preguntaInsertar1 = {_id: 0};
  @Input() preguntaInsertar2 = {_id: 0};
  @Input() preguntaInsertar3 = {_id: 0};
  @Input() preguntaInsertar4 = {_id: 0};
  @Input() preguntaInsertar5 = {_id: 0};
  @Input() preguntaInsertar6 = {_id: 0};
  @Input() preguntaInsertar7 = {_id: 0};
  @Input() preguntaInsertar8 = {_id: 0};
  @Input() preguntaInsertar9 = {_id: 0};
  @Input() preguntaInsertar10 = {_id: 0};
  @Input() dataMuestra = {_id : 0};
  cantPreguntas = 0;
  listaPreguntasInsertar = [];
  preguntasMostrar1: any = [];
  preguntasMostrar2: any = [];
  preguntasMostrar3: any = [];
  preguntasMostrar4: any = [];
  preguntasMostrar5: any = [];
  preguntasMostrar6: any = [];
  preguntasMostrar7: any = [];
  preguntasMostrar8: any = [];
  preguntasMostrar9: any = [];
  preguntasMostrar10: any = [];
  tipoPreguntas: any = [];
  opcionesCantidad = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  sugerenciasPreguntas: any = [];
  listaMuestraInsertar = [];
  dataMuestraMostrar: Encuestado[] = [];
  listaMuestraSeleccionada = [];

  estudioCambio = true;
  encuestaCambio = false;

  constructor(
    public estudioService: EstudioService,
    public subcategoriaService: SubcategoriaService,
    public lugarService: LugarService,
    public nivelsocioeconomicoService: NivelSocioEconomicoService,
    public analistaService: AnalistaService,
    public generoSerive: GeneroService,
    public categoriService: CategoriaService,
    /// Servicios de encuesta
    private servicio: EncuestaService,
    private servicioPregunta: PreguntaService,

    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder,
    private toast: ToastService,
    private ref: ChangeDetectorRef
  ) {this.createForm(); }

  ngAfterContentChecked() {
    this.ref.detectChanges();
  }

  ngOnInit(): void {
   this.loadEstudios();
  }

  loadEstudios(): void {
    this.estudioService.getEstudios().subscribe(data => {
      this.aux = data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
       this.estudios = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
    });
  }

  deleteEstudio(id): void {
    this.estudioService.deleteEstudio(id).subscribe(data => {
      this.aux=data;
      this.HandleErrorPostPut();
    });
  }



  Rechazar(){
    this.estudioData.estado="rechazado" ;
    this.estudioService.updateEstudioAdmin(this.estudioData._id, this.estudioData).subscribe(data => {
      this.aux=data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
        this.estudioCambio =false;
        this.encuestaCambio= true;
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
    });

    this.estudioService.rechazarEstudio(this.estudioData._id).subscribe(data => {});

  }

  Aprobar(){
    if(this.estudioData.estado != "rechazado"){
    console.log("entro a update");
    if (this.formEstudio.valid) {
      console.log("la vaidaciones son correctas");

          if(this.cambio == true){
            console.log("estado actual del cambio :"+ this.cambio);
            console.log("llamo validar los cambios del lugar");
            this.validarCambiosLugar();
            console.log("Hago la llamada de http con este estudio :");
            console.log(this.estudioData);
            this.estudioData.estado="procesado";

            this.estudioService.updateEstudioAdmin(this.estudioData._id, this.estudioData).subscribe(data => {
              this.aux=data;
              if(this.aux.estado == "Exitoso"){
                this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
                this.estudioCambio =false;
                this.encuestaCambio= true;
              }else{
                this.toast.showError(this.aux.estado,this.aux.mensaje)
              }
            });
             console.log("termine la llamada del http");
            this.cambio= false;
            location.reload();
            console.log("estado actual del cambio :"+ this.cambio);
          }else if(this.cambio == false){
            console.log("estado actual del cambio :"+ this.cambio);
            this.estudioData.estado="procesado";
            this.estudioService.updateEstudioAdmin(this.estudioData._id, this.estudioData).subscribe(data => {
              this.aux=data;
              if(this.aux.estado == "Exitoso"){
                this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
                this.estudioCambio =false;
                this.encuestaCambio= true;
              }else{
                this.toast.showError(this.aux.estado,this.aux.mensaje)
              }
            });
           //this.loadEstudios();
           //location.reload();
          }
    }
    else{
      this.toast.showError("error",'ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
  }else{
    this.toast.showError("error"," no se puede procesar")
  }

  this.encuesta.estudio._id = this.estudioData._id;
  //this.estudioService.updateEstudioAdmin(this.estudioData._id, this.estudioData).subscribe(data => {});
  this.cargarDataMuestra();

  }




  updateEstudio(){
    console.log("entro a update");

    if (this.formEstudio.valid) {
      console.log("la vaidaciones son correctas");

          if(this.cambio == true){
            console.log("estado actual del cambio :"+ this.cambio);
            console.log("llamo validar los cambios del lugar");
            this.validarCambiosLugar();
            console.log("Hago la llamada de http con este estudio :");
            console.log(this.estudioData);

            this.estudioService.updateEstudioAdmin(this.estudioData._id, this.estudioData).subscribe(data => {
             });
             console.log("termine la llamada del http");
            this.cambio= false;
            location.reload();
            console.log("estado actual del cambio :"+ this.cambio);
          }else if(this.cambio == false){
            console.log("estado actual del cambio :"+ this.cambio);
            this.estudioService.updateEstudioAdmin(this.estudioData._id, this.estudioData).subscribe(data => {
            });
           this.loadEstudios();
           location.reload();
          }
    }
    else{
      this.toast.showError('Es necesario llenar los todos los campos', 'Campos incompletos');
    }
  }

  editar(estudio): void{
    console.log(' estado actual de la variable estudioData');
    console.log(this.estudioData);

    this.estudioData = estudio;

    console.log(' estudio actual despues de asignar el estudio en estudio data');
    console.log(this.estudioData);

    this.addSubcategoria();
    this.addNivelSocioEconomico();
    this.addGenero();
    this.addAnalistas();
    this.addLugar();
    this.addGenero();
    this.addCategoria();
  }

  Cambio(): void {
    if (this.cambio === true){
        this.cambio = false;
    }else{
      this.cambio = true;
    }
  }

  // Metodo para cargar dropdown de género
  addGenero(): void {
    this.generoSerive.getGeneros().subscribe(data => {
      this.aux = data;
      if (this.aux.estado === 'Exitoso'){
        this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
        this.genero = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado, this.aux.mensaje);
      }
    });
  }

  // Metodo para cargar dropdown de categoría
  addCategoria(): void {
    this.categoriService.getCategorias().subscribe(data => {
      this.aux = data;
      if (this.aux.estado === 'Exitoso'){
        this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
        this.categorias = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado, this.aux.mensaje);
      }
    });
  }

  // Metodo para cargar dropdown de analista
  addAnalistas(): void {
    this.analistaService.getAnalistas().subscribe( data => {
      this.aux = data;
      if (this.aux.estado === 'Exitoso'){
        this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
        this.analistas = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado, this.aux.mensaje);
      }
    });
  }

  /// Busqueda para los drop de lugar por pais seleccionado previamente
  addLugar(): void{
    this.lugarService.getLugars().subscribe((Lugares: {}) => {
      this.aux = Lugares;
      if (this.aux.estado === 'Exitoso'){
        this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
        this.estados = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado, this.aux.mensaje);
      }
    });
  }

  // Metodo para cargar dropdown de municipios
  addMunicipios(id): void{
    this.lugarService.getMunicipio(id).subscribe((data: {}) => {
      this.aux = data;
      if (this.aux.estado === 'Exitoso'){
        this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
        this.municipios = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado, this.aux.mensaje);
      }
    });
  }

  addParroquia(id): void {
    this.lugarService.getParroquia(0, id).subscribe((data: {}) => {
      this.aux = data;
      if (this.aux.estado === 'Exitoso'){
        this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
        this.parroquias = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado, this.aux.mensaje);
      }
    });
  }

  /// Esto es para mostrar en los drops doww
  addSubcategoria(): void{
    this.subcategoriaService.getsubcategorias().subscribe((data: {}) => {
      this.aux = data;
      if (this.aux.estado === 'Exitoso'){
        this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
        this.subcategoria = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado, this.aux.mensaje);
      }
    });
  }

  addNivelSocioEconomico(): void{
    this.nivelsocioeconomicoService.getNivelesSocioEconomicos().subscribe((data: {}) => {
      this.aux = data;
      if (this.aux.estado === 'Exitoso'){
        this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
        this.nivelSocioEconomico = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado, this.aux.mensaje);
      }
    });
  }

  /////////////// Llamadas a los drop al haber un cambio ////////////////////////////////////
  busquedaMunicipio(id): void{
    // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
    if (id > 0 ){
      this.auxEstadoID = id;         // El ID es del estado
      this.addMunicipios(id);
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

  validarCambiosLugar(): void{

    console.log('entro en la llamada de las validaciones de lugar');

    if ((this.estudioData.lugar._id !== this.parroquiaData._id) && (this.parroquiaData._id === this.auxParroquiaID)){
      console.log('la parroquia es diferente y hago el cambio');
      console.log('id de la parroquia anterior :' + this.estudioData.lugar._id);
      this.estudioData.lugar._id = this.auxParroquiaID;
      console.log('id de la parroquia Actual :' + this.estudioData.lugar._id);
    }

    if ((this.estudioData.lugar.lugar._id !== this.municipioData._id) && (this.municipioData._id === this.auxMunicipioID)){
      this.estudioData.lugar.lugar._id = this.auxMunicipioID;
    }

    if ((this.estudioData.lugar.lugar.lugar._id !== this.estadoData._id) && (this.estadoData._id === this.auxEstadoID)){
      this.estudioData.lugar.lugar.lugar._id = this.auxEstadoID;
    }

  }

  // Validaciones de Pregunta
  get nombreEstudio(){return this.formEstudio.get('nombreEstudio'); }
  // get comentarioAnalistaEstudio(){return this.formEstudio.get('comentarioAnalistaEstudio'); }
  get edadMinimaEstudio(){return this.formEstudio.get('edadMinimaEstudio'); }
  get edadMaximaEstudio(){return this.formEstudio.get('edadMaximaEstudio'); }
  // get estadoEstudio(){return this.formEstudio.get('estadoEstudio'); }
  get fechaInicioEstudio(){return this.formEstudio.get('fechaInicioEstudio'); }
  get fechaFinEstudio(){return this.formEstudio.get('fechaFinEstudio');
 }
  get lugarEstudio(){return this.formEstudio.get('lugarEstudio'); }
  get lugarmunicipio(){return this.formEstudio.get('lugarmunicipio'); }
  get lugarparroquia(){return this.formEstudio.get('lugarparroquia'); }

  get EstadoData(){return this.formEstudio.get('EstadoData'); }
  get MunicipioData(){return this.formEstudio.get('MunicipioData'); }
  get ParroquiaData(){return this.formEstudio.get('ParroquiaData'); }

  get analistaEstudio(){return this.formEstudio.get('analistaEstudio'); }
  get categoriaEstudio(){return this.formEstudio.get('categoriaEstudio'); }
  get subcategoriaEstudio(){return this.formEstudio.get('subcategoriaEstudio'); }
  get nivelEstudio(){return this.formEstudio.get('nivelEstudio'); }
  get GeneroEstudio(){return this.formEstudio.get('GeneroEstudio'); }
  get CategoriaEstudio(){return this.formEstudio.get('CategoriaEstudio'); }

    //////////////////////////////////////////////////// Metodos Para la parte de encuesta

    ///// Metodos para las validaciones
    get fechaInicioEncuesta(){ return this.formEncuesta.get('fechaInicioEncuesta'); }
    get fechaFinEncuesta(){return this.formEncuesta.get('fechaFinEncuesta');}
    get estudio(){return this.formEncuesta.get('estudio');}
    get pregunta1(){ return this.formEncuesta.get('pregunta1');}
  get pregunta2(){ return this.formEncuesta.get('pregunta2');}
  get pregunta3(){ return this.formEncuesta.get('pregunta3');}
  get pregunta4(){ return this.formEncuesta.get('pregunta4');}
  get pregunta5(){ return this.formEncuesta.get('pregunta5');}
  get pregunta6(){ return this.formEncuesta.get('pregunta6');}
  get pregunta7(){ return this.formEncuesta.get('pregunta7');}
  get pregunta8(){ return this.formEncuesta.get('pregunta8');}
  get pregunta9(){ return this.formEncuesta.get('pregunta9');}
  get pregunta10(){ return this.formEncuesta.get('pregunta10');}

  get Genero(){return this.formEstudio.get('Genero'); }


  createForm(): void {
    this.formEstudio = this.formBuilder.group({
      nombreEstudio: ['', [Validators.required, Validators.pattern(this.patronNombreEstudio)]],
      lugarEstudio: [''],
      lugarmunicipio: [''],
      lugarparroquia: [''],
      analistaEstudio: ['', Validators.required],

      EstadoData: ['' ],
      MunicipioData: [''],
      ParroquiaData: [''],


      edadMinimaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      edadMaximaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      //categoriaEstudio: ['', [Validators.required]],
      subcategoriaEstudio: ['', [Validators.required]],
      nivelEstudio: [''],
      GeneroEstudio:[''],
      CategoriaEstudio:[''],

      Genero:''

    });

    this.formEncuesta = this.formBuilder.group({
      pregunta1:[''],
      pregunta2:[''],
      pregunta3:[''],
      pregunta4:[''],
      pregunta5:[''],
      pregunta6:[''],
      pregunta7:[''],
      pregunta8:[''],
      pregunta9:[''],
      pregunta10:[''],
    });
  }


  // Metodo para la creacion de la encuesta del estudio
  addEncuesta(): any{
    this.encuesta.estudio._id = this.estudioData._id;
    this.encuesta.encuestados = this.listaMuestraInsertar;
    console.log(this.encuesta);
    if (this.formEncuesta.valid && this.listaPreguntasInsertar.length === this.cantPreguntas) {
      this.encuesta.preguntas = this.listaPreguntasInsertar;
      this.servicio.createEncuesta(this.encuesta).subscribe((data: {}) => {
        console.log(this.encuesta);
        this.toast.showSuccess('La encuesta ha sido creada', 'Creada satisfactoriamente');
        setTimeout(() => {}, 4500);
        this.encuesta = {
          _id: 0,
          estado: '',
          fechaInicio: '',
          fechaFin: '',
          estudio: {_id: 0},
          preguntas: [],
          encuestados: []
        };
        //location.reload();
        this.limpiar();
      },
      (error) => {
        this.toast.showError('Error de conexión', 'Intentelo más tarde');
      });
    }
    else{
       this.toast.showError('Campos Incompletos', 'Es necesario llenar los todos los campos');
    }
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

  // agregar pregunta a la lista de preguntas
  addPreguntaEncuesta(preguntaInsertar): void {
    if (preguntaInsertar._id !== 0){
      this.listaPreguntasInsertar.push(preguntaInsertar);
      console.log(this.listaPreguntasInsertar);
    }else {
      this.toast.showError('Seleccion incorrecta', 'Seleccione una pregunta válida');
    }
  }

  // // aqui yo mando el id del estudio para que me devuelva una lista de preguntas
  // // asociadas a la subcategoria del estudio
  cargarPreguntas(i: number): void {
    console.log('estudio');
    console.log(this.estudioData._id);
    this.servicioPregunta.getPreguntasXSubcategoria(this.encuesta.estudio._id).subscribe((data: {}) => {
      this.aux = data;
      this.HandleErrorGetPreguntas();
      console.log('preguntas');
      console.log(this.preguntasMostrar1);
    });
    this.cantPreguntas = i;
  }

  sugerirPreguntas(): void{
    // aqui le mandas el id del estudio
    console.log('estudio', this.estudioData._id);
    this.servicioPregunta.sugerirPreguntas(this.estudioData._id).subscribe((data: {}) => {
      this.aux = data;
      this.HandleErrorGetSugerencias();
      console.log('sugerencaias');
      console.log(this.sugerenciasPreguntas);
    });
  }

  cargarDataMuestra(): void {
    this.estudioService.getDataMuestra(this.encuesta.estudio._id).subscribe(data => {
      this.aux = data;
      this.HandleErrorGetPoblacion();
      console.log('datamuestra');
      console.log(this.dataMuestraMostrar);
    });
  }

  limpiar(): void{
    this.preguntaInsertar1 = {_id: 0};
    this.preguntaInsertar2 = {_id: 0};
    this.preguntaInsertar3 = {_id: 0};
    this.preguntaInsertar4 = {_id: 0};
    this.preguntaInsertar5 = {_id: 0};
    this.preguntaInsertar6 = {_id: 0};
    this.preguntaInsertar7 = {_id: 0};
    this.preguntaInsertar8 = {_id: 0};
    this.preguntaInsertar9 = {_id: 0};
    this.preguntaInsertar10 = {_id: 0};
    this.cantPreguntas = 0;
  }

  // Estudio
  HandleErrorGet(): void {
    if (this.aux.estado === 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
      this.estudios = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }

  // Preguntas
  HandleErrorGetPreguntas(): void {
    if (this.aux.estado === 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
      this.preguntasMostrar1 = this.aux.objeto;
      this.preguntasMostrar2 = this.aux.objeto;
      this.preguntasMostrar3 = this.aux.objeto;
      this.preguntasMostrar4 = this.aux.objeto;
      this.preguntasMostrar5 = this.aux.objeto;
      this.preguntasMostrar6 = this.aux.objeto;
      this.preguntasMostrar7 = this.aux.objeto;
      this.preguntasMostrar8 = this.aux.objeto;
      this.preguntasMostrar9 = this.aux.objeto;
      this.preguntasMostrar10 = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }

  // Poblacion
  HandleErrorGetPoblacion(): void {
    if (this.aux.estado === 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
      this.dataMuestraMostrar = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }

  // Sugerencias
  HandleErrorGetSugerencias(): void {
    if (this.aux.estado === 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
      this.sugerenciasPreguntas = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
    }
  }

  HandleErrorPostPut(): void {
    if (this.aux.estado === 'Exitoso'){
      this.toast.showSuccess(this.aux.estado, this.aux.mensaje);
     }else{
      this.toast.showError(this.aux.estado, this.aux.mensaje);
     }
  }

}
