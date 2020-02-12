package com.slusarz.pokerafterdark.application.usecase.createplayer;

import com.slusarz.pokerafterdark.application.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.application.events.EventBus;
import com.slusarz.pokerafterdark.application.permission.RequiredAdministrationPermission;
import com.slusarz.pokerafterdark.application.usecase.createplayer.event.PlayerCreatedEvent;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePlayerCommandHandler implements CommandHandler<CreatePlayerCommandResult, CreatePlayerCommand> {

    private PlayerRepository playerJpaRepository;

    private CreatePlayerValidator createPlayerValidator;

    private EventBus eventBus;

    @Override
    @RequiredAdministrationPermission
    public CreatePlayerCommandResult handle(CreatePlayerCommand createPlayerCommand) {
        PlayerId playerId = playerJpaRepository.generateId();
        Player player = Player.newPlayer(playerId, createPlayerCommand.getPlayerName());
        createPlayerValidator.validate(player);
        playerJpaRepository.save(player);
        eventBus.fireEvent(PlayerCreatedEvent.of(playerId));
        return CreatePlayerCommandResult.of(playerId);
    }
}
