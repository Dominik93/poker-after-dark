import { Host } from "../game/host"
import { TournamentParticipant } from "./tournament-participant";

export class Tournament {

    id: string;

    host: Host;

    date: string;

    pot: number;

    participantsCount: number;

    participants: TournamentParticipant[];

}
