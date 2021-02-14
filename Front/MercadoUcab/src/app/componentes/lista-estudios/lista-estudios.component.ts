import { Component, Input, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Estudio } from 'src/app/models/estudio';
import { Lugar } from 'src/app/models/Lugar';
import { NivelSocioEconomico } from 'src/app/models/nivel-socio-economico';
import { Subcategoria } from 'src/app/models/subcategoria';
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
  @Input() estudioData = {_id: 0, nombre: '', estado: '', comentarioAnalista : '', edadMinima: 0, edadMaxima: 0 , fechaInicio: '', fechaFin: '',
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
  genero:any;
  categorias:any;

  ///// Atributos para la busqueda de acuerdo a lo seleccionado
  lugar: any;
  parroquias: any;
  estados: any;
  municipios: any;
  auxEstadoID: number;
  auxMunicipioID: number;
  auxParroquiaID: number;
  aux:any=[];
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
    preguntas: []
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
  @Input() preguntaInsertar11 = {_id: 0};
  @Input() preguntaInsertar12 = {_id: 0};
  @Input() preguntaInsertar13 = {_id: 0};
  @Input() preguntaInsertar14 = {_id: 0};
  @Input() preguntaInsertar15 = {_id: 0};
  cantPreguntas = 0;
  listaPreguntasInsertar = [];
  preguntasMostrar: any = [];
  tipoPreguntas: any = [];
  opcionesCantidad = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  sugerenciasPreguntas: any = [];
  listaMuestraInsertar = [];

  @Input() dataMuestra = {_id : 0};

  estudioCambio =true;
  encuestaCambio= false;

  constructor(
    public estudioService: EstudioService,
    public subcategoriaService: SubcategoriaService,
    public lugarService: LugarService,
    public nivelsocioeconomicoService: NivelSocioEconomicoService,
    public analistaService: AnalistaService,
    public generoSerive:GeneroService,
    public categoriService:CategoriaService,
    /// Servicios de encuesta
    private servicio: EncuestaService,
    private servicioPregunta: PreguntaService,

      
    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder,
    private toast:ToastService
    ) {this.createForm(); }

  ngOnInit(): void {
   this.loadEstudios();
  }

  loadEstudios(): void {
    this.estudioService.getEstudios().subscribe(data => {
      this.aux = data;
      this.HandleErrorGet();
    });
  }

  deleteEstudio(id) {
    this.estudioService.deleteEstudio(id).subscribe(data => {
      this.loadEstudios();
    });
  }

  Aprobar(){
    this.estudioCambio =false;
  this.encuestaCambio= true;
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
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
 }

 editar(estudio): void{
  console.log(" estado actual de la variable estudioData");
  console.log(this.estudioData);



  this.estudioData= estudio;

  console.log(" estudio actual despues de asignar el estudio en estudio data");
  console.log(this.estudioData);

  this.addSubcategoria();
  this.addNivelSocioEconomico();
  this.addAnalistas();
  this.addLugar();
  this.addGenero();
  this.addCategoria();
}

  Cambio(){
      if(this.cambio == true){
         this.cambio= false;
      }else{
        this.cambio= true;
      }
  }


  addGenero(){
    this.generoSerive.getGeneros().subscribe(data=>{
      this.aux = data;
    if(this.aux.estado == "Exitoso"){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
     this.genero = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje)
    } 
    })

  }

  addCategoria(){
    this.categoriService.getCategorias().subscribe(data=>{
      this.aux = data;
    if(this.aux.estado == "Exitoso"){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
     this.categorias = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje)
    } 
    })

  }

addAnalistas(){
  this.analistaService.getAnalistas().subscribe( data =>{
    this.aux = data;
    if(this.aux.estado == "Exitoso"){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
     this.analistas = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje)
    } 
  })
}

/// Busqueda para los drop de lugar por pais seleccionado previamente
addLugar(){
  this.lugarService.getLugars().subscribe((Lugares: {}) => {
    this.aux = Lugares;
    if(this.aux.estado == "Exitoso"){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
     this.estados = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje)
    }
  });
}

addMunicipios(id){
  this.lugarService.getMunicipio(id).subscribe((data: {}) => {
    this.aux = data;
    if(this.aux.estado == "Exitoso"){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
     this.municipios = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje)
    }
  });
}

addParroquia(id){
  this.lugarService.getParroquia(0,id).subscribe((data: {}) => {
    this.aux = data;
    if(this.aux.estado == "Exitoso"){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
     this.parroquias = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje)
    }
  });
}

/////////////// Llamadas a los drop al haber un cambio ////////////////////////////////////
busquedaMunicipio(id){
  // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
  if (id > 0 ){
      this.auxEstadoID=id         // El ID es del estado
          this.addMunicipios(id)
  }
}

busquedaParroquia(id){
  // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
  if (id > 0 ){
      this.auxMunicipioID= id;
        this.addParroquia(id)
  }
}

seleccionarParroquia(id){
  this.auxParroquiaID = id;
}

  validarCambiosLugar(){



    console.log("entro en la llamada de las validaciones de lugar");

    if((this.estudioData.lugar._id != this.parroquiaData._id) && (this.parroquiaData._id == this.auxParroquiaID)){
      console.log("la parroquia es diferente y hago el cambio");
      console.log("id de la parroquia anterior :"+ this.estudioData.lugar._id);
      this.estudioData.lugar._id =this.auxParroquiaID;
      console.log("id de la parroquia Actual :"+ this.estudioData.lugar._id);
    }

    if((this.estudioData.lugar.lugar._id != this.municipioData._id) && (this.municipioData._id == this.auxMunicipioID)){
      this.estudioData.lugar.lugar._id =this.auxMunicipioID;
    }

    if((this.estudioData.lugar.lugar.lugar._id != this.estadoData._id) && (this.estadoData._id == this.auxEstadoID)){
      this.estudioData.lugar.lugar.lugar._id =this.auxEstadoID;
    }

  }


  /// Esto es para mostrar en los drops doww
  addSubcategoria(){
    this.subcategoriaService.getsubcategorias().subscribe((data: {}) => {
      this.aux = data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
       this.subcategoria = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
    });
  }

  addNivelSocioEconomico(){
    this.nivelsocioeconomicoService.getNivelesSocioEconomicos().subscribe((data: {}) => {
      this.aux = data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
       this.nivelSocioEconomico = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
    });
  }




  // Validaciones de Pregunta
  get nombreEstudio(){return this.formEstudio.get('nombreEstudio'); }
  get comentarioAnalistaEstudio(){return this.formEstudio.get('comentarioAnalistaEstudio'); }
  get edadMinimaEstudio(){return this.formEstudio.get('edadMinimaEstudio'); }
  get edadMaximaEstudio(){return this.formEstudio.get('edadMaximaEstudio'); }
  get estadoEstudio(){return this.formEstudio.get('estadoEstudio'); }
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
  get subcategoriaEstudio(){return this.formEstudio.get('subcategoriaEstudio'); }
  get nivelEstudio(){return this.formEstudio.get('nivelEstudio'); }
  get GeneroEstudio(){return this.formEstudio.get('GeneroEstudio'); }
  get CategoriaEstudio(){return this.formEstudio.get('CategoriaEstudio'); }

    //////////////////////////////////////////////////// Metodos Para la parte de encuesta

    ///// Metodos para las validaciones
    get fechaInicioEncuesta(){ return this.formEncuesta.get('fechaInicioEncuesta'); }
    get fechaFinEncuesta(){return this.formEncuesta.get('fechaFinEncuesta');}
    get estudio(){return this.formEncuesta.get('estudio');}
    get pregunta(){ return this.formEncuesta.get('pregunta');}

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


      //fechaInicioEstudio: ['', [Validators.required, Validators.pattern(this.patronFechaEstudio)]],
      //fechaFinEstudio: ['', [Validators.pattern(this.patronFechaEstudio)]],
      edadMinimaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      edadMaximaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      comentarioAnalistaEstudio: ['', Validators.pattern(this.patronNombreEstudio)],
      subcategoriaEstudio: ['', [Validators.required]],
      nivelEstudio: [''],
      GeneroEstudio:[''],
      CategoriaEstudio:[''],

    });

   this.formEncuesta = this.formBuilder.group({
      fechaInicioEncuesta: ['', [Validators.required, Validators.pattern(this.patronFechaEstudio)]],
      fechaFinEncuesta: ['', [ Validators.pattern(this.patronFechaEstudio)]],
      estudio: ['', Validators.required],
      pregunta: ['', Validators.required],
    });
  }


  
  // Metodo para la creacion de la encuesta del estudio
   addEncuesta(): any{
     console.log(this.encuesta);
     if (this.formEncuesta.valid) {
       this.encuesta.preguntas = this.listaPreguntasInsertar;
       this.servicio.createEncuesta(this.encuesta).subscribe((data: {}) => {
         this.toast.showSuccess('La encuesta ha sido creada', 'Creada satisfactoriamente');
       },
       (error) => {
         this.toast.showError('Error de conexión', 'Intentelo más tarde');
       });
       this.encuesta = {
         _id: 0,
         estado: '',
         fechaInicio: '',
         fechaFin: '',
         estudio: {_id: 0},
         preguntas: []
       };
       location.reload();
     }
     else{
       this.toast.showError('Campos Incompletos', 'ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
     }
   }


    // // aqui yo mando el id del estudio para que me devuelva una lista de preguntas
  // // asociadas a la subcategoria del estudio
   cargarPreguntas(i: number): void {
     this.servicioPregunta.getPreguntasXSubcategoria(this.encuesta.estudio._id).subscribe((data: {}) => {
       this.preguntasMostrar = data;
     });
     this.cantPreguntas = i;
   }

  sugerirPreguntas(): void{
    // aqui le mandas el id del estudio
    this.servicioPregunta.sugerirPreguntas(this.aux).subscribe((data: {}) => {
      this.sugerenciasPreguntas = data;
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
    this.preguntaInsertar11 = {_id: 0};
    this.preguntaInsertar12 = {_id: 0};
    this.preguntaInsertar13 = {_id: 0};
    this.preguntaInsertar14 = {_id: 0};
    this.preguntaInsertar15 = {_id: 0};
    this.cantPreguntas = 0;
  }

  
  HandleErrorGet(){
    if(this.aux.estado == "Exitoso"){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
     this.estudios = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje)
    } 
  }
  
  HandleErrorPostPut(){
    if(this.aux.estado == "Exitoso"){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
     }else{
     this.toast.showError(this.aux.estado,this.aux.mensaje)
     }
  }
}
