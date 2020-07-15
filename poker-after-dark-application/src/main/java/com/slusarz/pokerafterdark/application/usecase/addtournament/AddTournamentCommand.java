package com.slusarz.pokerafterdark.application.usecase.addtournament;

import com.slusarz.pokerafterdark.application.cqrs.command.Command;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.domain.tournament.TournamentParticipant;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value(staticConstructor = "of")
public class AddTournamentCommand implements Command {

    private PlayerId host;

    private LocalDate occurrenceDate;

    private Pot pot;

    private List<TournamentParticipant> participants;

}
