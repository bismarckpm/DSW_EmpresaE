import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { EstudioService } from 'src/app/services/estudio.service';
import { LugarService } from 'src/app/services/lugar.service';
import { NivelSocioEconomicoService } from 'src/app/services/nivel-socio-economico.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';

@Component({
  selector: 'app-form-estudio-cliente',
  templateUrl: './form-estudio.component.html',
  styleUrls: ['./form-estudio.component.css']
})
export class FormEstudioClienteComponent implements OnInit {
  @Input() estudio = {_id: 0, nombre: '', estado: '' , comentarioAnalista: '', edadMinima: 0, edadMaxima: 0 , fechaInicio: '', fechaFin: '',
  lugar : {_id: 0, estado: '', nombre: '', tipo: '',lugar : {_id: 0, estado: '', nombre: '', tipo: '',lugar : {_id: 0, estado: '', nombre: '', tipo: '',lugar : {_id: 0, estado: '', nombre: '', tipo: ''}}}},
  nivelSocioEconomico: {_id: 0, nombre: '', estado: '', descripcion: ''},
  subcategoria : {_id: 0, nombre: '', estado: ''},
 };

  nivelSocioEconomico: any;
  subcategoria: any;
  analistas:any;



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
  patronFechaEstudio: any = /^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$/;
  patronNombreEstudio: any = /^[A-Za-z\s]+$/;

  constructor(
    private formBuilder: FormBuilder,
    public estudioService: EstudioService,
    public lugarService: LugarService,
    public subcategoriaService: SubcategoriaService,
    public nivelsocioeconomicoService: NivelSocioEconomicoService
    ) {
    this.createForm();
  }

  ngOnInit(): void {
    this.addLugar();
    this.addNivelSocioEconomico();
    this.addSubcategoria();
  }

  agregarEstudio(): void {
    console.log('agregó estudio');
  }

  addEstudio() {
    if (this.formEstudio.valid) {
      this.estudioService.createEstudioCliente(JSON.parse(localStorage.getItem("usuarioID")),this.estudio).subscribe((data: {}) => {
      });
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
  }


/// Busqueda para los drop de lugar por pais seleccionado previamente
addLugar(){
  this.lugarService.getLugars().subscribe((Lugares: {}) => {
    this.estados = Lugares;
  });
}

busquedaMunicipio(id){
  // El ID es del estado
  this.auxEstadoID = id;
  // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
  if (id > 0 ){
    this.lugarService.getMunicipio(this.auxEstadoID).subscribe((data: {}) => {
      this.municipios = data;
    });
  }

}

busquedaParroquia(id){
  // El ID es del estado
  this.auxMunicipioID = id;
  // Esta peticion se realiza para mostar todas las parroquias aasociadas al estado
  if (id > 0 ) {
    this.lugarService.getParroquia(this.auxMunicipioID, id).subscribe((data: {}) => {
      this.parroquias = data;
    });
  }
}

seleccionarParroquia(id){
  this.auxParroquiaID = id;
}

  /////////////// Dropdowns ///////////////////////
  addSubcategoria(){
    this.subcategoriaService.getsubcategorias().subscribe((data: {}) => {
      this.subcategoria = data;
    });
  }

  addNivelSocioEconomico(){
    this.nivelsocioeconomicoService.getNivelesSocioEconomicos().subscribe((data: {}) => {
      this.nivelSocioEconomico = data;
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

  createForm(): void {
    this.formEstudio = this.formBuilder.group({
      nombreEstudio: ['', [Validators.required, Validators.pattern(this.patronNombreEstudio)]],
      estadoEstudio: ['', Validators.required],
      fechaInicioEstudio: ['', [Validators.required, Validators.pattern(this.patronFechaEstudio)]],
      fechaFinEstudio: ['', [ Validators.pattern(this.patronFechaEstudio)]],
      edadMinimaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      edadMaximaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      lugarEstudio: ['', [Validators.required]],
      subcategoriaEstudio: ['', [Validators.required]],
      nivelEstudio: ['', [Validators.required]],
    });
  }

}
