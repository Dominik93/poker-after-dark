package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameCommand;
import com.slusarz.pokerafterdark.application.usecase.addgame.AddGameCommandResult;
import com.slusarz.pokerafterdark.domain.game.Pot;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.specification.api.AddGameRequest;
import com.slusarz.pokerafterdark.specification.api.AddGameResponse;
import com.slusarz.pokerafterdark.specification.api.Game;
import com.slusarz.pokerafterdark.spring.delivery.mapper.command.CommandMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AddGameMapper implements CommandMapper<AddGameRequest, AddGameCommand, AddGameCommandResult, AddGameResponse> {

    @Override
    public AddGameResponse toResponse(AddGameCommandResult result) {
        return AddGameResponse.builder().success(true).build();
    }

    @Override
    public AddGameCommand toCommand(AddGameRequest addGameRequest) {
        return toAddGameCommand(addGameRequest.getGame(), addGameRequest.getSkipValidation());
    }

    private AddGameCommand toAddGameCommand(Game game, boolean skipValidation) {
        PlayerId playerId = PlayerId.of(game.getHost().getId());
        Pot pot = Pot.of(game.getPot());
        LocalDate date = Optional.ofNullable(game.getDate()).orElse(LocalDate.now());
        List<Participant> participants = toParticipants(game.getParticipants());
        return AddGameCommand.of(playerId, date, pot, participants, skipValidation);
    }

    private List<Participant> toParticipants(List<com.slusarz.pokerafterdark.specification.api.Participant> participants) {
        return participants.stream().map(this::toParticipant).collect(Collectors.toList());
    }

    private Participant toParticipant(com.slusarz.pokerafterdark.specification.api.Participant participant) {
        return Participant.of(PlayerId.of(participant.getPlayerId()), Earnings.of(participant.getEarnings().doubleValue()));
    }
}
