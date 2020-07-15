import { PlayerInTournament } from "./player-in-tournament";

export class TournamentParticipant {

    playerId: string;

    earnings: number;

    place: number;

    constructor(player: PlayerInTournament) {
        this.playerId = player.player.id;
        this.earnings = player.earnings;
        this.place = player.place;
    }

}
