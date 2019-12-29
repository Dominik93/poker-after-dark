package com.slusarz.pokerafterdark.aplication.usecase.createplayer;

import com.slusarz.pokerafterdark.aplication.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePlayerCommandHandler implements CommandHandler<CreatePlayerCommandResult, CreatePlayerCommand> {

    private PlayerRepository playerJpaRepository;

    @Override
    public CreatePlayerCommandResult handle(CreatePlayerCommand createPlayerCommand) {
        PlayerId playerId = playerJpaRepository.generateId();
        Player player = Player.newPlayer(playerId, createPlayerCommand.getPlayerName());
        playerJpaRepository.save(player);
        return CreatePlayerCommandResult.of(playerId);
    }
}
