package com.slusarz.pokerafterdark.application.usecase.addgame;

import com.slusarz.pokerafterdark.application.game.GameQueryRepository;
import com.slusarz.pokerafterdark.application.usecase.addgame.exception.GameBeforeLastGameRuntimeException;
import com.slusarz.pokerafterdark.application.usecase.addgame.exception.PotNotMatchRuntimeException;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AddGameValidator {

    private GameQueryRepository gameQueryRepository;

    public void validate(Game game) {
        Game lastGame = gameQueryRepository.readLast();
        if (lastGame.getDate().isAfter(game.getDate())) {
            throw new GameBeforeLastGameRuntimeException(lastGame, game);
        }
    }

    public void validatePot(final List<Participant> participants) {
        double sum = participants.stream()
                .map(Participant::getEarnings)
                .map(Earnings::getValue)
                .mapToDouble(Double::doubleValue)
                .sum();
        if (sum != 0.0) {
            throw new PotNotMatchRuntimeException(sum);
        }
    }

}