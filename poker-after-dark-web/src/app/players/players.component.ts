import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource, MatSort } from '@angular/material';
import { Player } from '../model/player';
import { PlayersService } from '../players.service';

@Component({
  selector: 'app-players',
  templateUrl: './players.component.html',
  styleUrls: ['./players.component.css']
})
export class PlayersComponent implements OnInit {

  displayedColumns = ["name", "liveEarnings", "biggestWin" , "biggestLose", "gamesPlayed"];
  dataSource: MatTableDataSource<Player>;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private router: Router,
    private playersService: PlayersService) { }

  ngOnInit() {
    this.playersService.getPlayers().subscribe(data => {
      this.dataSource = new MatTableDataSource(data.players);
      this.dataSource.sort = this.sort;
    })

  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
