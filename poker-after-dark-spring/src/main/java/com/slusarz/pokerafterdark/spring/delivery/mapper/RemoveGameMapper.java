package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommand;
import com.slusarz.pokerafterdark.application.usecase.removegame.RemoveGameCommandResult;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.specification.api.RemoveGameResponse;
import com.slusarz.pokerafterdark.spring.delivery.mapper.command.CommandMapper;
import org.springframework.stereotype.Component;

@Component
public class RemoveGameMapper implements CommandMapper<String, RemoveGameCommand, RemoveGameCommandResult, RemoveGameResponse> {

    @Override
    public RemoveGameResponse toResponse(RemoveGameCommandResult commandResult) {
        return RemoveGameResponse.builder()
                .success(true)
                .build();
    }

    @Override
    public RemoveGameCommand toCommand(String gameId) {
        return RemoveGameCommand.of(GameId.of(gameId));
    }
}
