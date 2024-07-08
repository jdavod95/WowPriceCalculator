import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-modal',
  templateUrl: './confirm-modal.component.html',
  styleUrl: './confirm-modal.component.scss'
})
export class ConfirmModalComponent {
  
  constructor(public dialogRef: MatDialogRef<ConfirmModalComponent>) {}
  
  close(): void {
    this.dialogRef.close();
  }
}
