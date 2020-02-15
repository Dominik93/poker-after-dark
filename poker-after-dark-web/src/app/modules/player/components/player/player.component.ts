import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { PlayersService } from 'src/app/core/services/players.service';
import { Player } from 'src/app/shared/models/player';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent implements OnInit {

  title = '';

  player;

  playerIds = [];

  private routeSub: Subscription;

  constructor(private route: ActivatedRoute,
    private playerService: PlayersService) { }

  ngOnInit() {
    this.routeSub = this.route.params.subscribe(params => {
      console.log(params)
      console.log(params['id'])
      this.playerService.getPlayer(params['id']).subscribe(data => {
        this.player = data.player;
        this.title = 'Live winninngs of ' + this.player.name;
        this.playerIds.push(this.player.id);
      }
      )
    });
  }

  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }

}
