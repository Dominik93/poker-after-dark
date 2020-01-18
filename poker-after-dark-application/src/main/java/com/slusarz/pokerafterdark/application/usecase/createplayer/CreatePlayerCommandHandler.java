package com.slusarz.pokerafterdark.application.usecase.createplayer;

import com.slusarz.pokerafterdark.application.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.application.permission.RequiredAdministrationPermission;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePlayerCommandHandler implements CommandHandler<CreatePlayerCommandResult, CreatePlayerCommand> {

    private PlayerRepository playerJpaRepository;

    @Override
    @RequiredAdministrationPermission
    public CreatePlayerCommandResult handle(CreatePlayerCommand createPlayerCommand) {
        PlayerId playerId = playerJpaRepository.generateId();
        Player player = Player.newPlayer(playerId, createPlayerCommand.getPlayerName());
        playerJpaRepository.save(player);
        return CreatePlayerCommandResult.of(playerId);
    }
}
