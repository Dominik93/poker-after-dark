package com.slusarz.pokerafterdark.application.usecase.removegame;

import com.slusarz.pokerafterdark.application.common.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.application.common.events.EventBus;
import com.slusarz.pokerafterdark.application.common.permission.RequiredAdministrationPermission;
import com.slusarz.pokerafterdark.application.usecase.removegame.event.RemoveGameEvent;
import com.slusarz.pokerafterdark.application.usecase.removegame.validator.RemoveGameValidator;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveGameCommandHandler implements CommandHandler<RemoveGameCommandResult, RemoveGameCommand> {

    private EventBus eventsBus;

    private GameRepository gameJpaRepository;

    private RemoveGameValidator removeGameValidator;

    @Override
    @RequiredAdministrationPermission
    public RemoveGameCommandResult handle(RemoveGameCommand removeGameCommand) {
        GameId gameId = removeGameCommand.getGameId();

        removeGameValidator.validate(gameId);

        gameJpaRepository.remove(gameId);
        eventsBus.fireEvent(RemoveGameEvent.of(gameId));
        return RemoveGameCommandResult.of();
    }

}
