import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-prompt-dialog',
  templateUrl: './prompt-dialog.component.html',
  styleUrls: ['./prompt-dialog.component.css']
})
export class PromptDialogComponent {

  question;
  answer = [];
  options = [];
  
  myControl = new FormControl();

  constructor(
    public dialogRef: MatDialogRef<PromptDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.question = data.question;
      this.options = data.options;
    }

  onNoClick(): void {
    this.dialogRef.close();
  }

  isEmptyOptions() {
    return this.options.length == 0;
  }


}
