package com.slusarz.pokerafterdark.application.usecase.addgame;

import com.slusarz.pokerafterdark.application.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.application.events.EventBus;
import com.slusarz.pokerafterdark.application.permission.RequiredAdministrationPermission;
import com.slusarz.pokerafterdark.application.usecase.addgame.event.AddGameEvent;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AddGameCommandHandler implements CommandHandler<AddGameCommandResult, AddGameCommand> {

    private EventBus eventsBus;

    private GameRepository gameJpaRepository;

    private AddGameValidator addGameValidator;

    @Override
    @RequiredAdministrationPermission
    public AddGameCommandResult handle(AddGameCommand addGameCommand) {
        GameId gameId = gameJpaRepository.generateId();
        Game game = Game.of(gameId,
                addGameCommand.getHost(),
                addGameCommand.getDate(),
                addGameCommand.getPot(),
                addGameCommand.getParticipants());
        validate(addGameCommand, game);

        game = gameJpaRepository.save(game);
        eventsBus.fireEvent(AddGameEvent.of(game));
        return AddGameCommandResult.of();
    }

    private void validate(AddGameCommand command, Game game) {
        addGameValidator.validate(game);
        if (!command.isSkipPotValidation()) {
            addGameValidator.validatePot(game.getParticipants());
        }
    }

}
