import { Host } from "./host"
import { Participant } from "./participant";
import { GameActions } from "./game-actions";

export class Game {

    id: string;

    actions: GameActions;

    type: string;

    host: Host;

    date: string;

    pot: number;

    participantsCount: number;

    participants: Participant[];

}
