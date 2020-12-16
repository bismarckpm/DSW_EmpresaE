import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Estudio } from 'src/app/models/estudio';
import { Lugar } from 'src/app/models/Lugar';
import { NivelSocioEconomico } from 'src/app/models/nivel-socio-economico';
import { Subcategoria } from 'src/app/models/subcategoria';
import { AnalistaService } from 'src/app/services/analista.service';
import { EstudioService } from 'src/app/services/estudio.service';
import { LugarService } from 'src/app/services/lugar.service';
import { NivelSocioEconomicoService } from 'src/app/services/nivel-socio-economico.service';
import { SubcategoriaService } from 'src/app/services/subcategoria.service';

@Component({
  selector: 'app-lista-estudios',
  templateUrl: './lista-estudios.component.html',
  styleUrls: ['./lista-estudios.component.css']
})
export class ListaEstudiosComponent implements OnInit {


  // Declaracion de variables
  estudios: Estudio[] = [];
  _id = this.actRoute.snapshot.params._id;
  @Input() estudioData = {_id: 0, nombre: '', estado: '', comentarioAnalista : '', edadMinima: 0, edadMaxima: 0 , fechaInicio: '', fechaFin: '',
             lugar : {_id: 0, estado: '', nombre: '', tipo: '', lugar : {_id: 0, estado: '', nombre: '', tipo: '', lugar : {_id: 0, estado: '', nombre: '', tipo: '',}}},
             nivelSocioEconomico: {_id: 0, nombre: '', estado: '', descripcion: ''},
             subcategoria : {_id: 0, nombre: '', estado: ''},
             analista:{_id:0}
            };
  // Declaracion para los dropdown
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

  // Declaracion para validar
  formEstudio: FormGroup;
  patronEdadEstudio: any = /^(0?[0-9]{1,2}|1[0-7][0-9]|99)$/;
  patronFechaEstudio: any = /^([0-2][0-9]|3[0-1])(\/|-)(0[1-9]|1[0-2])\2(\d{4})$/;
  patronNombreEstudio: any = /^[A-Za-z\s]+$/;


  constructor(
    public estudioService: EstudioService,
    public subcategoriaService: SubcategoriaService,
    public lugarService: LugarService,
    public nivelsocioeconomicoService: NivelSocioEconomicoService,
    public analistaService:AnalistaService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder
    ) {this.createForm(); }

  ngOnInit(): void {
   this.loadEstudios();
  }

  loadEstudios(): void {
    this.estudioService.getEstudios().subscribe(data => {
      this.estudios = data;
    });
  }

  deleteEstudio(id) {
    this.estudioService.deleteEstudio(id).subscribe(data => {
      this.loadEstudios();
    });
  }

  updateEstudio(){
    if (this.formEstudio.valid) {
    this.estudioService.updateEstudio(this.estudioData._id, this.estudioData).subscribe(data => {
     });
    this.loadEstudios();
    }
    else{
      alert('ES NECESARIO LLENAR LOS TODOS LOS CAMPOS');
    }
 }

 editar(estudio): void{
  this.addSubcategoria();
  this.addLugar();
  this.addNivelSocioEconomico();
  this.addAnalistas();
  this.estudioData = estudio;
 
}
addAnalistas(){
  this.analistaService.getAnalistas().subscribe( data =>{
    this.analistas=data;
  })
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


  /// Esto es para mostrar en los drops doww
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




  // Validaciones de Pregunta
  get nombreEstudio(){return this.formEstudio.get('nombreEstudio'); }
  get comentarioAnalistaEstudio(){return this.formEstudio.get('comentarioAnalistaEstudio'); }
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
      fechaFinEstudio: ['', [Validators.pattern(this.patronFechaEstudio)]],
      edadMinimaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      edadMaximaEstudio: ['', [Validators.required, Validators.maxLength(2), Validators.pattern(this.patronEdadEstudio)]],
      comentarioAnalistaEstudio: ['', Validators.pattern(this.patronNombreEstudio)],
      lugarEstudio: ['', [Validators.required]],
      subcategoriaEstudio: ['', [Validators.required]],
      nivelEstudio: ['', [Validators.required]],

    });
  }
}

