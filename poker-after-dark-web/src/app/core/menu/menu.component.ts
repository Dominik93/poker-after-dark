import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { LoginDialogComponent } from '../login-dialog/login-dialog.component';
import { LoginService } from '../services/login.service';
import { AdministrationService } from '../services/administration.service';
import { LoginRequest } from 'src/app/shared/models/login/login-request';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  administrationMode = false;

  constructor(public dialog: MatDialog,
    private administrationService: AdministrationService,
    private loginService: LoginService) { }

  ngOnInit() {
  }

  onAdministration() {
    const dialogRef = this.dialog.open(LoginDialogComponent, {
      width: '250px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result !== false) {
        var request = new LoginRequest();
        request.password = result;
        this.loginService.login(request).subscribe(data => {
          if (data.success) {
            this.administrationMode = true;
            this.administrationService.setAdministrationMode(true, data.token);
          }
        });
      }
    });
  }

  redirect(url) {
    console.log(url);
    window.location.href = url;
  }
}
