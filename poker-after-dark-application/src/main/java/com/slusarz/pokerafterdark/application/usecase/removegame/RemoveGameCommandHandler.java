package com.slusarz.pokerafterdark.application.usecase.removegame;

import com.slusarz.pokerafterdark.application.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.application.events.EventBus;
import com.slusarz.pokerafterdark.application.usecase.addgame.GameRepository;
import com.slusarz.pokerafterdark.application.usecase.removegame.event.RemoveGameEvent;
import com.slusarz.pokerafterdark.domain.game.GameId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveGameCommandHandler implements CommandHandler<RemoveGameCommandResult, RemoveGameCommand> {

    private EventBus eventsBus;

    private GameRepository gameJpaRepository;

    @Override
    public RemoveGameCommandResult handle(RemoveGameCommand removeGameCommand) {
        GameId gameId = removeGameCommand.getGameId();
        gameJpaRepository.remove(gameId);
        eventsBus.fireEvent(RemoveGameEvent.of(gameId));
        return RemoveGameCommandResult.of();
    }

}
