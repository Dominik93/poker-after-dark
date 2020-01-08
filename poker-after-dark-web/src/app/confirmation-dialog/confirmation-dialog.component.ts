import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-confirmation-dialog',
  templateUrl: './confirmation-dialog.component.html',
  styleUrls: ['./confirmation-dialog.component.css']
})
export class ConfirmationDialogComponent {

  question;
  
  answer: boolean;
  
  constructor(
    public dialogRef: MatDialogRef<ConfirmationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.question = data.question;
      this.answer = data.answer;
    }

  onNoClick(): void {
    this.data.answer = false;
    this.dialogRef.close();
  }

  onOkClick(): void {
    this.data.answer = true;
    this.dialogRef.close();
  }

}
