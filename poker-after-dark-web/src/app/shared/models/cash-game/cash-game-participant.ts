import { PlayerInGame } from "./player-in-game";

export class CashGameParticipant {

    playerId: string;

    earnings: number;

    constructor(player: PlayerInGame) {
        this.playerId = player.player.id;
        this.earnings = player.earnings;
    }

}
