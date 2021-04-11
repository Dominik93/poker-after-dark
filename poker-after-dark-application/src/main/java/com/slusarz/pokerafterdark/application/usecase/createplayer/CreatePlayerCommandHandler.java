package com.slusarz.pokerafterdark.application.usecase.createplayer;

import com.slusarz.pokerafterdark.application.common.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.application.common.events.EventBus;
import com.slusarz.pokerafterdark.application.common.permission.RequiredAdministrationPermission;
import com.slusarz.pokerafterdark.application.usecase.createplayer.event.PlayerCreatedEvent;
import com.slusarz.pokerafterdark.application.usecase.createplayer.validator.CreatePlayerValidator;
import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePlayerCommandHandler implements CommandHandler<CreatePlayerCommandResult, CreatePlayerCommand> {

    private PlayerRepository playerJpaRepository;

    private CreatePlayerValidator createPlayerValidator;

    private EventBus eventBus;

    @Override
    @RequiredAdministrationPermission
    public CreatePlayerCommandResult handle(CreatePlayerCommand createPlayerCommand) {
        createPlayerValidator.validate(createPlayerCommand.getPlayerName());
        PlayerId playerId = playerJpaRepository.generateId();
        Player player = Player.newPlayer(playerId, createPlayerCommand.getPlayerName());
        playerJpaRepository.save(player);
        eventBus.fireEvent(PlayerCreatedEvent.of(playerId));
        return CreatePlayerCommandResult.of(playerId);
    }
}
