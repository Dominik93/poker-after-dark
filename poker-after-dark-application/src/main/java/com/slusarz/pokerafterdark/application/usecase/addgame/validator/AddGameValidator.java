package com.slusarz.pokerafterdark.application.usecase.addgame.validator;

import com.slusarz.pokerafterdark.application.usecase.addgame.exception.GameBeforeLastGameRuntimeException;
import com.slusarz.pokerafterdark.application.usecase.addgame.exception.PotNotMatchRuntimeException;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
public class AddGameValidator {

    private GameRepository gameRepository;

    public void validateOccurrenceDate(LocalDate occurrenceDate) {
        Game lastCashGame = gameRepository.readLast();
        if (lastCashGame.getOccurrenceDate().isAfter(occurrenceDate)) {
            throw new GameBeforeLastGameRuntimeException(lastCashGame, occurrenceDate);
        }
    }

    public void validatePot(final List<Earnings> earnings) {
        double sum = earnings.stream()
                .map(Earnings::getValue)
                .mapToDouble(Double::doubleValue)
                .sum();
        if (sum != 0.0) {
            throw new PotNotMatchRuntimeException(sum);
        }
    }

}