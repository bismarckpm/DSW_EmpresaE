import {Component, Input, OnInit} from '@angular/core';
import {Analista} from '../../models/analista';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AnalistaService} from '../../services/analista.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-lista-analista',
  templateUrl: './lista-analista.component.html',
  styleUrls: ['./lista-analista.component.css']
})
export class ListaAnalistaComponent implements OnInit {

  Analista: Analista[] = [];
  _id = this.actRoute.snapshot.params._id;
  @Input() analistaData = {_id: 0, username: '', clave: '', correoElectronico: '', estado: ''};

  formAnalista: FormGroup;
  patronCorreoAnalista: any = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

  constructor(
    public analistaService: AnalistaService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private formBuilder: FormBuilder
  ) {
    this.createForm();
  }

  ngOnInit(): void {
    this.loadAnalista();
  }

  loadAnalista(): void{
    this.analistaService.getAnalistas().subscribe(data => {
      this.Analista = data;
    });
  }

  deleteAnalista(id): void{
    this.analistaService.deleteAnalista(id).subscribe(data => {
      this.loadAnalista();
    });
  }

  updateAnalista(): void{
    if (this.formAnalista.valid) {
      this.analistaService.updateAnalista(this.analistaData._id, this.analistaData).subscribe(data => {
      });
    }
    else{
      alert('FILL ALL FIELDS');
    }
  }

  editar(analista): void{
    this.analistaData = analista;
  }

  get usernameAnalista(){
    return this.formAnalista.get('usernameAnalista');
  }

  get claveAnalista(){
    return this.formAnalista.get('claveAnalista');
  }

  get estadoAnalista(){
    return this.formAnalista.get('estadoAnalista');
  }

  get correoElectronicoAnalista(){
    return this.formAnalista.get('correoElectronicoAnalista');
  }

  createForm(): void{
    this.formAnalista = this.formBuilder.group({
      usernameAnalista: ['', Validators.required],
      estadoAnalista: ['',  Validators.required],
      claveAnalista: ['', Validators.required],
      correoElectronicoAnalista: ['', [Validators.required, Validators.pattern(this.patronCorreoAnalista)]],
    });
  }
}
