import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatSort } from '@angular/material';
import { Player } from '../model/player';
import { PlayersService } from '../players.service';
import { AdministrationService } from '../administration.service';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent implements OnInit {

  administrationMode = false;
  displayedColumns = ["name", "liveEarnings", "biggestWin" , "biggestLose", "gamesPlayed"];
  dataSource: MatTableDataSource<Player>;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private router: Router,
    private administrationService: AdministrationService,
    private playersService: PlayersService) { }

  ngOnInit() {
    this.administrationMode = this.administrationService.currentAdministrationMode;
    this.administrationService.getAdministrationMode().subscribe(data => {
      this.administrationMode = data;
    })
    this.playersService.getPlayers().subscribe(data => {
      this.dataSource = new MatTableDataSource(data.players);
      this.dataSource.sort = this.sort;
    })

  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  
  onAddPlayer() {
    this.router.navigate(['/players/new']);
  }

}
