package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.application.usecase.addtournament.AddTournamentCommand;
import com.slusarz.pokerafterdark.application.usecase.addtournament.AddTournamentCommandResult;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;
import com.slusarz.pokerafterdark.domain.tournament.Place;
import com.slusarz.pokerafterdark.domain.tournament.TournamentParticipant;
import com.slusarz.pokerafterdark.specification.api.AddGameResponse;
import com.slusarz.pokerafterdark.specification.api.AddTournamentRequest;
import com.slusarz.pokerafterdark.specification.api.Tournament;
import com.slusarz.pokerafterdark.spring.delivery.mapper.command.CommandMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AddTournamentMapper implements CommandMapper<AddTournamentRequest, AddTournamentCommand,
        AddTournamentCommandResult, AddGameResponse> {

    @Override
    public AddGameResponse toResponse(AddTournamentCommandResult result) {
        return AddGameResponse.builder().success(true).build();
    }

    @Override
    public AddTournamentCommand toCommand(AddTournamentRequest addTournamentRequest) {
        return toAddTournamentCommand(addTournamentRequest.getTournament());
    }

    private AddTournamentCommand toAddTournamentCommand(Tournament tournament) {
        PlayerId playerId = PlayerId.of(tournament.getHost().getId());
        Pot pot = Pot.of(tournament.getPot());
        LocalDate date = Optional.ofNullable(tournament.getDate()).orElse(LocalDate.now());
        List<TournamentParticipant> tournamentParticipants = toParticipants(tournament.getParticipants());
        return AddTournamentCommand.of(playerId, date, pot, tournamentParticipants);
    }

    private List<TournamentParticipant> toParticipants(List<com.slusarz.pokerafterdark.specification.api.TournamentParticipant> participants) {
        return participants.stream().map(this::toParticipant).collect(Collectors.toList());
    }

    private TournamentParticipant toParticipant(com.slusarz.pokerafterdark.specification.api.TournamentParticipant participant) {
        return TournamentParticipant.of(PlayerId.of(participant.getPlayerId()),
                Earnings.of(participant.getEarnings().doubleValue()),
                Place.of(participant.getPlace()));
    }
}
