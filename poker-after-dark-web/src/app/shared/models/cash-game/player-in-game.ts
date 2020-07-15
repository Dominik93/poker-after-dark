import { Player } from "../player/player";

export class PlayerInGame {

    player: Player;

    buyIn: number;

    winningsInChips: number;

    winningsInMoney: number;

    earnings: number;

    constructor(player: Player) {
        this.player = player;
        this.winningsInChips = 0;
        this.winningsInMoney = 0;
        this.buyIn = 1;
    }

}
