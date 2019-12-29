package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.aplication.usecase.addgame.AddGameCommand;
import com.slusarz.pokerafterdark.aplication.usecase.addgame.AddGameCommandResult;
import com.slusarz.pokerafterdark.domain.game.Pot;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.specification.model.game.AddGameRequest;
import com.slusarz.pokerafterdark.specification.model.game.AddGameResponse;
import com.slusarz.pokerafterdark.specification.model.game.Game;
import com.slusarz.pokerafterdark.spring.delivery.mapper.command.CommandMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddGameMapper implements CommandMapper<AddGameRequest, AddGameCommand, AddGameCommandResult, AddGameResponse> {

    @Override
    public AddGameResponse toResponse(AddGameCommandResult result) {
        return AddGameResponse.builder().success(true).build();
    }

    @Override
    public AddGameCommand toCommand(AddGameRequest addGameRequest) {
        return toAddGameCommand(addGameRequest.getGame(), addGameRequest.isSkipValidation());
    }

    private AddGameCommand toAddGameCommand(Game game, boolean skipValidation) {
        return AddGameCommand.of(PlayerId.of(game.getHost().getId()),
                game.getDate(),
                Pot.of(game.getPot()),
                toParticipants(game.getParticipants()), skipValidation);
    }

    private List<Participant> toParticipants(List<com.slusarz.pokerafterdark.specification.model.game.Participant> participants) {
        return participants.stream().map(this::toParticipant).collect(Collectors.toList());
    }

    private Participant toParticipant(com.slusarz.pokerafterdark.specification.model.game.Participant participant) {
        return Participant.of(PlayerId.of(participant.getPlayerId()), Earnings.of(participant.getEarnings()));
    }
}
