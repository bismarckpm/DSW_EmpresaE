import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AnalistaService} from '../../services/analista.service';

@Component({
  selector: 'app-analista',
  templateUrl: './analista.component.html',
  styleUrls: ['./analista.component.css']
})
export class AnalistaComponent implements OnInit {

  constructor(
    private ruta: ActivatedRoute,
    private servicio: AnalistaService,
    private rutaActual: Router
  ) { }

  ngOnInit(): void {
  }

}
