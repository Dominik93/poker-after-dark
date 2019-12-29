package com.slusarz.pokerafterdark.aplication.usecase.removegame;

import com.slusarz.pokerafterdark.aplication.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.aplication.events.EventBus;
import com.slusarz.pokerafterdark.aplication.usecase.addgame.GameRepository;
import com.slusarz.pokerafterdark.aplication.usecase.removegame.event.RemoveGameEvent;
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
