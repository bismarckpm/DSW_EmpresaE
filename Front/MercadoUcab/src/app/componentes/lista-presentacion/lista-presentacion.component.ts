import { Component, Input, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {Presentacion} from '../../models/presentacion';
import {PresentacionService} from '../../services/presentacion.service';

@Component({
  selector: 'app-lista-presentacion',
  templateUrl: './lista-presentacion.component.html',
  styleUrls: ['./lista-presentacion.component.css']
})
export class ListaPresentacionComponent implements OnInit {
 

  presentaciones: Presentacion[] = [];
  id=this.actRoute.snapshot.params['id'];
  @Input() presentacionData={id:0,  descripcion:'',estado:''};
  presentacion:any;

    //Declaracion para las Validaciones
    formPresentacion: FormGroup;
    namePattern: any = /^[A-Za-z0-9\s]+$/;
 
  constructor(
    public presentacionService:PresentacionService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder
  ) { this.createForm();}

  ngOnInit(): void {
    this.loadPresentacion();

  }

  loadPresentacion():void {
    this.presentacionService.getPresentaciones().subscribe(data => {
      this.presentaciones = data;
    })
  }

  deletePresentacion(id) {

      this.presentacionService.deletePresentacion(id).subscribe(data => {
        this.loadPresentacion()
      })

  }

  updatePresentacion(){
     this.presentacionService.updatePresentacion(this.presentacionData.id, this.presentacionData).subscribe(data => {
      })

  }

  editar(presentacion){
    this.presentacionData= presentacion;
  }


   /// Validacion de Datos
   get descripcionpresentacion(){
    return this.formPresentacion.get('descripcionpresentacion');
  }

  get estadopresentacion(){
    return this.formPresentacion.get('estadopresentacion');
  }



  createForm(){
    this.formPresentacion = this.formBuilder.group({
      descripcionpresentacion: ['', [Validators.pattern(this.namePattern), Validators.required]],
      estadopresentacion: ['', Validators.required],
    });
  }

}
