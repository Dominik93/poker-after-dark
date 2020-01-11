import { PlayerInGame } from "./player-in-game";

export class Participant {

    playerId: string;

    earnings: number;

    constructor(player: PlayerInGame) {
        this.playerId = player.player.id;
        this.earnings = player.earnings;
    }

}
