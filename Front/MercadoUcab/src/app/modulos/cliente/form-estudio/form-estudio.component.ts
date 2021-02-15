import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EstudioService } from 'src/app/services/estudio.service';
import { GeneroService } from 'src/app/services/genero.service';
import { LugarService } from 'src/app/services/lugar.service';
import { NivelSocioEconomicoService } from 'src/app/services/nivel-socio-economico.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';
import { ToastService } from 'src/app/services/toast.service';

@Component({
  selector: 'app-form-estudio-cliente',
  templateUrl: './form-estudio.component.html',
  styleUrls: ['./form-estudio.component.css']
})
export class FormEstudioClienteComponent implements OnInit {
  @Input() estudio = {_id: 0, estado:'',nombre: '' , edadMinima: 0, edadMaxima: 0 ,via:'',
  genero:{_id:0},
  lugar : {_id: 0, estado: '', nombre: '', tipo: '',lugar : {_id: 0, estado: '', nombre: '', tipo: '',lugar : {_id: 0, estado: '', nombre: '', tipo: '',lugar : {_id: 0, estado: '', nombre: '', tipo: ''}}}},
  nivelSocioEconomico: {_id: 0 },
  subcategoria : {_id: 0 }
 };

  nivelSocioEconomico: any;
  subcategoria: any;
  analistas:any;
  genero:any
  aux:any=[];



   ///// Atributos para la busqueda de acuerdo a lo seleccionado
  lugar: any;
  parroquias: any;
  estados:any;
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
    public toast:ToastService,
    public generoSerive:GeneroService,
    public nivelsocioeconomicoService: NivelSocioEconomicoService
    ) {
    this.createForm();
  }

  ngOnInit(): void {
    this.addLugar();
    this.addNivelSocioEconomico();
    this.addSubcategoria();
    this.addGenero();
  }

  agregarEstudio(): void {
    console.log('agregó estudio');
  }

  addEstudio() {
    if (this.formEstudio.valid) {
      this.estudio.lugar._id = this.auxParroquiaID;
      this.estudio.estado="solicitado";
      console.log(this.estudio);

      this.estudioService.createEstudioCliente(JSON.parse(localStorage.getItem("usuarioID")),this.estudio).subscribe((data: {}) => {
        this.aux=data;
        if(this.aux.estado == "Exitoso"){
          this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
         this.municipios = this.aux.objeto;
        }else{
          this.toast.showError(this.aux.estado,this.aux.mensaje)
        }
      });
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
    //location.reload();
  }


/// Busqueda para los drop de lugar por pais seleccionado previamente
addLugar(){
  this.lugarService.getLugars().subscribe((Lugares: {}) => {
    this.aux=Lugares;
    if(this.aux.estado == "Exitoso"){
      this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
     this.estados = this.aux.objeto;
    }else{
      this.toast.showError(this.aux.estado,this.aux.mensaje)
    }
  });
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

busquedaMunicipio(id){
  // El ID es del estado
  this.auxEstadoID = id;
  // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
  if (id > 0 ){
    this.lugarService.getMunicipio(this.auxEstadoID).subscribe((data: {}) => {
      this.aux=data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
       this.municipios = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
    });
  }

}

busquedaParroquia(id){
  // El ID es del estado
  this.auxMunicipioID = id;
  // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
  if (id > 0 ) {
    this.lugarService.getParroquia(this.auxMunicipioID, id).subscribe((data: {}) => {
      this.aux=data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
       this.parroquias = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
    });
  }
}

seleccionarParroquia(id){
  this.auxParroquiaID = id;
}

  /////////////// Dropdowns ///////////////////////
  addSubcategoria(){
    this.subcategoriaService.getsubcategorias().subscribe((data: {}) => {
      this.aux=data;
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
      this.aux=data;
      if(this.aux.estado == "Exitoso"){
        this.toast.showSuccess(this.aux.estado,this.aux.mensaje)
       this.nivelSocioEconomico = this.aux.objeto;
      }else{
        this.toast.showError(this.aux.estado,this.aux.mensaje)
      }
    });
  }



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
  get viaEstudio(){return this.formEstudio.get('viaEstudio'); }
  get GeneroEstudio(){return this.formEstudio.get('GeneroEstudio'); }

  createForm(): void {
    this.formEstudio = this.formBuilder.group({
      nombreEstudio: ['', [Validators.required, Validators.pattern(this.patronNombreEstudio)]],
      //fechaInicioEstudio: ['', [Validators.required, Validators.pattern(this.patronFechaEstudio)]],
      //fechaFinEstudio: '',
      edadMinimaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      edadMaximaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      lugarEstudio: ['', [Validators.required]],
      subcategoriaEstudio: ['', [Validators.required]],
      nivelEstudio: ['', [Validators.required]],
      viaEstudio: ['', [Validators.required]],
      GeneroEstudio: ['', [Validators.required]],

    });
  }

}