import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-modal',
  templateUrl: './confirm-modal.component.html',
  styleUrl: './confirm-modal.component.scss'
})
export class ConfirmModalComponent {
  
  public modalTitle: string | undefined;
  public modalMessage: string | undefined;
  
  constructor(public dialogRef: MatDialogRef<ConfirmModalComponent>) {}
  
  ok(): void {
    this.dialogRef.close(true);
  }

  cancel(): void {
    this.dialogRef.close();
  }
}
