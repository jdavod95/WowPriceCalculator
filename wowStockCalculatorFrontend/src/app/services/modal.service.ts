import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmModalComponent } from '../components/confirm-modal/confirm-modal.component';

@Injectable({
  providedIn: 'root'
})
export class ModalService {
  constructor(private dialog: MatDialog) {}

  openModal(): void {
    this.dialog.open(ConfirmModalComponent, {
      width: '300px'
    });
  }

  closeModal(): void {
    this.dialog.closeAll();
  }
}
