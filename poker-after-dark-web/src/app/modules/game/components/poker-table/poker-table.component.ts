import { Component, Input, OnInit } from '@angular/core';
import { Participant } from 'src/app/shared/models/game/participant';
import { Player } from 'src/app/shared/models/player/player';


export class Seat {
  seat: number;
  label: string = '';
  score: number;
  theme: string = 'metalic-theme';

  constructor(seat: number) {
    this.seat = seat;
  }

}

@Component({
  selector: 'app-poker-table',
  templateUrl: './poker-table.component.html',
  styleUrls: ['./poker-table.component.css']
})
export class PokerTableComponent implements OnInit {

  seatMap = new Map([
    [2,  [9,13]],
    [3,  [9,13,18]],
    [4,  [4,9,13,18]],
    [5,  [3,5,9,13,18]],
    [6,  [3,5,9,13,17,19]],
    [7,  [3,4,5,9,13,17,19]],
    [8,  [3,4,5,9,13,17,18,19]],
    [9,  [3,5,9,13,16,17,18,19,20]],
    [10, [2,3,5,6,9,13,16,17,19,20]],
    [11, [2,3,4,5,6,9,13,16,17,19,20]],
    [12, [2,3,4,5,6,9,13,16,17,18,19,20]]
  ]); 
  places = new Map([
    [1, 'gold-theme'],
    [2, 'silver-theme'],
    [3, 'bronze-theme'],
  ]); 

  potPlace = 11;

  seats: Seat[] = [
    new Seat(1), new Seat(2), new Seat(3), new Seat(4), new Seat(5), new Seat(6), new Seat(7),
    new Seat(8), new Seat(9), new Seat(10), new Seat(11), new Seat(12), new Seat(13), new Seat(14), 
    new Seat(15), new Seat(16), new Seat(17), new Seat(18), new Seat(19), new Seat(20), new Seat(21), 
  ];

  private __participants: Participant[];

  @Input()
  private players: Player[];

  @Input()
  private pot: number;

  constructor() { }

  ngOnInit() {
  }

  get participants(): Participant[] {
    return this.__participants;
  }

  @Input() set participants(participants: Participant[]) {
    this.__participants = participants;
    this.clearSeats();
    const seats = this.seatMap.get(this.__participants.length);
    this.__participants.forEach((participant, index) => {
        const seat = this.findSeat(seats[index]);
        seat.label = this.getPlayerName(participant.playerId);
        seat.score = participant.earnings
        seat.theme = isNaN((participant.place)) ?
         'metalic-theme' :
          this.places.get(participant.place) || 'black-theme'
    })
    this.findSeat(this.potPlace).label = "POT: " + this.pot;
  }

  getPlayerName(playerId: string) {
    return this.players.find(player => player.id === playerId).name;
  }

  private findSeat(seatNumber: number) : Seat {
    return this.seats.find(s => s.seat === seatNumber);
  }

  private clearSeats() {
    this.seats.forEach(seat => seat.label = '');
    this.seats.forEach(seat => seat.score = null);
    this.seats.forEach(seat => seat.theme = 'metalic-theme');
  }

}
