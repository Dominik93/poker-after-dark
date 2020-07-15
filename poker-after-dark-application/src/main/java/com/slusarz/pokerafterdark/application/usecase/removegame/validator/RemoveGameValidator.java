package com.slusarz.pokerafterdark.application.usecase.removegame.validator;

import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveGameValidator {

    private GameRepository gameRepository;

    public void validate(final GameId gameId) {
        Game game = gameRepository.readLast();
        if (!gameId.equals(game.getGameId())) {
            throw new GameIsNotLastException(gameId);
        }

    }

}
