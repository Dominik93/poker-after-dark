package com.slusarz.pokerafterdark.spring.delivery.mapper;

import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerCommand;
import com.slusarz.pokerafterdark.application.usecase.createplayer.CreatePlayerCommandResult;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.specification.api.AddPlayerRequest;
import com.slusarz.pokerafterdark.specification.api.AddPlayerResponse;
import com.slusarz.pokerafterdark.spring.common.delivery.command.CommandMapper;
import org.springframework.stereotype.Component;

@Component
public class AddPlayerMapper implements CommandMapper<AddPlayerRequest, CreatePlayerCommand, CreatePlayerCommandResult, AddPlayerResponse> {

    @Override
    public CreatePlayerCommand toCommand(AddPlayerRequest addPlayerRequest) {
        return CreatePlayerCommand.of(PlayerName.of(addPlayerRequest.getPlayerName().trim()));
    }

    @Override
    public AddPlayerResponse toResponse(CreatePlayerCommandResult commandResult) {
        return AddPlayerResponse.builder().success(true).playerId(commandResult.getPlayerId().getId()).build();
    }
}
