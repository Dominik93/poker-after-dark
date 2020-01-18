package com.slusarz.pokerafterdark.application.usecase.addgame;

import com.slusarz.pokerafterdark.application.cqrs.command.CommandHandler;
import com.slusarz.pokerafterdark.application.events.EventBus;
import com.slusarz.pokerafterdark.application.permission.RequiredAdministrationPermission;
import com.slusarz.pokerafterdark.application.usecase.addgame.event.AddGameEvent;
import com.slusarz.pokerafterdark.application.usecase.addgame.exception.AddGameRuntimeException;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AddGameCommandHandler implements CommandHandler<AddGameCommandResult, AddGameCommand> {

    private EventBus eventsBus;

    private GameRepository gameJpaRepository;

    @Override
    @RequiredAdministrationPermission
    public AddGameCommandResult handle(AddGameCommand addGameCommand) {
        validate(addGameCommand);
        GameId gameId = gameJpaRepository.generateId();

        Game game = Game.of(gameId,
                addGameCommand.getHost(),
                addGameCommand.getDate(),
                addGameCommand.getPot(),
                addGameCommand.getParticipants());

        game = gameJpaRepository.save(game);
        eventsBus.fireEvent(AddGameEvent.of(game));
        return AddGameCommandResult.of();
    }

    private void validate(AddGameCommand addGameCommand) {
        if (!addGameCommand.isSkipValidation()) {
            validatePot(addGameCommand.getParticipants());
        }
    }

    private void validatePot(final List<Participant> participants) {
        double sum = participants.stream()
                .map(Participant::getEarnings)
                .map(Earnings::getValue)
                .mapToDouble(Double::doubleValue)
                .sum();
        if (sum != 0.0) {
            throw new AddGameRuntimeException();
        }
    }

}
