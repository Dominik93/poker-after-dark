import { Component, OnInit } from '@angular/core';
import { AdministrationService } from '../../../../core/services/administration.service';
import { PlayersService } from 'src/app/core/services/players.service';
import { AddPlayerRequest } from 'src/app/shared/models/add-player-request';

@Component({
  selector: 'app-add-player',
  templateUrl: './add-player.component.html',
  styleUrls: ['./add-player.component.css']
})
export class AddPlayerComponent implements OnInit {

  playerName: string;

  constructor(private playersService: PlayersService,
    private administrationService: AdministrationService) { }

  ngOnInit() {
    if (!this.administrationService.currentAdministrationMode) {
      window.location.href = '#';
    }
  }

  onSave() {
    var request = new AddPlayerRequest();
    request.playerName = this.playerName;
    this.playersService.addplayer(request).subscribe(data => {
      console.log(data.success);
    })
  }

}
