package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.aplication.usecase.removegame.RemoveGameCommandResult;
import com.slusarz.pokerafterdark.specification.model.game.RemoveGameResponse;
import com.slusarz.pokerafterdark.spring.delivery.mapper.command.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class RemoveGameMapper implements ResultMapper<RemoveGameCommandResult, RemoveGameResponse> {

    @Override
    public RemoveGameResponse toResponse(RemoveGameCommandResult commandResult) {
        return RemoveGameResponse.builder()
                .success(true)
                .build();
    }
}
