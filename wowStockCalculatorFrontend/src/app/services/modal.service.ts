import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmModalComponent } from '../components/confirm-modal/confirm-modal.component';

@Injectable({
  providedIn: 'root'
})
export class ModalService {
  constructor(private dialog: MatDialog) {}

  confirmationModal(title?: string, message?: string, onOk?: () => void ): void {
    let dialog = this.dialog.open(ConfirmModalComponent, {
      width: '300px'
    });
    dialog.componentInstance.modalTitle = title;
    dialog.componentInstance.modalMessage = message;
    dialog.afterClosed().subscribe(isOk => {
      if(isOk && onOk != null){
        onOk();
      }
    })
  }

  closeModal(): void {
    this.dialog.closeAll();
  }
}
