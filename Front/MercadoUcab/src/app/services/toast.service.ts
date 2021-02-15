import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor(private toastr: ToastrService) { }

  showSuccess(msj, titulo): void {
    this.toastr.success(msj, titulo, {
      progressBar: true
    });
  }

  showError(error, msj): void {
    this.toastr.error(error, msj, {
      progressBar: true
    });
  }

  showInfo(msj, titulo): void {
    this.toastr.info(msj, titulo, {
      progressBar: true
    });
  }

  showWarning(msj, titulo): void {
    this.toastr.warning(msj, titulo, {
      progressBar: true
    });
  }
}