import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {EncuestadoService} from '../../services/encuestado.service';

@Component({
  selector: 'app-encuestado',
  templateUrl: './encuestado.component.html',
  styleUrls: ['./encuestado.component.css']
})
export class EncuestadoComponent implements OnInit {

  constructor(
    private ruta: ActivatedRoute,
    private servicio: EncuestadoService,
    private rutaActual: Router
  ) { }

  ngOnInit(): void {
  }

}
