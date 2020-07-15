package com.slusarz.pokerafterdark.application.usecase.removegame.validator;

import com.slusarz.pokerafterdark.domain.game.GameId;

public class GameIsNotLastException extends RuntimeException {

    public GameIsNotLastException(GameId gameId) {
        super("Game with id [" + gameId + "] is not last.");
    }

}
