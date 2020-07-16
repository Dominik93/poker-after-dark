package com.slusarz.pokerafterdark.application.usecase.addtournament;

import com.slusarz.pokerafterdark.application.common.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.application.common.events.EventBus;
import com.slusarz.pokerafterdark.application.common.permission.RequiredAdministrationPermission;
import com.slusarz.pokerafterdark.application.usecase.addgame.validator.AddGameValidator;
import com.slusarz.pokerafterdark.application.usecase.addtournament.event.AddTournamentEvent;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.domain.tournament.Tournament;
import com.slusarz.pokerafterdark.domain.tournament.TournamentId;
import com.slusarz.pokerafterdark.domain.tournament.TournamentParticipant;
import com.slusarz.pokerafterdark.domain.tournament.TournamentRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class AddTournamentCommandHandler implements CommandHandler<AddTournamentCommandResult, AddTournamentCommand> {

    private EventBus eventsBus;

    private TournamentRepository tournamentRepository;

    private AddGameValidator addGameValidator;

    @Override
    @RequiredAdministrationPermission
    public AddTournamentCommandResult handle(AddTournamentCommand addTournamentCommand) {
        validate(addTournamentCommand);

        TournamentId tournamentId = tournamentRepository.generateId();

        List<TournamentParticipant> participants = addTournamentCommand.getParticipants();
        PlayerId host = addTournamentCommand.getHost();
        Pot addTournamentCommandPot = addTournamentCommand.getPot();
        LocalDate occurrenceDate = addTournamentCommand.getOccurrenceDate();
        Tournament tournament = Tournament.of(tournamentId, participants, host, occurrenceDate, addTournamentCommandPot);

        tournamentRepository.save(tournament);

        eventsBus.fireEvent(AddTournamentEvent.of(tournament));

        return AddTournamentCommandResult.of();
    }


    private void validate(AddTournamentCommand addTournamentCommand) {
        addGameValidator.validateOccurrenceDate(addTournamentCommand.getOccurrenceDate());
        addGameValidator.validatePot(addTournamentCommand.getParticipants().stream()
                .map(TournamentParticipant::getEarnings).collect(Collectors.toList()));
    }

}
