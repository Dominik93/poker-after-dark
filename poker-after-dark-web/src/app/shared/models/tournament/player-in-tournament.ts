import { Player } from "../player/player";

export class PlayerInTournament {

    player: Player;

    place: number;

    percent: number;

    winningsInMoney: number;

    earnings: number;

    constructor(player: Player) {
        this.player = player;
        this.place = 0;
        this.percent = 0;
    }

}
