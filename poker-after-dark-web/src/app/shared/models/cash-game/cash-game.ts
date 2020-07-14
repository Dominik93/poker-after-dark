import { Host } from "../game/host"
import { CashGameParticipant } from "./cash-game-participant";

export class CashGame {

    id: string;

    host: Host;

    date: string;

    pot: number;

    participantsCount: number;

    participants: CashGameParticipant[];

}
