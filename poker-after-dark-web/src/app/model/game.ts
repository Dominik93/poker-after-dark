import { Participant } from "./participant";
import { Host } from "./host"

export class Game {

    id: string;

    host: Host;

    date: Date;

    pot: number;

    participantsCount: number;

    participants: Participant[];

}
