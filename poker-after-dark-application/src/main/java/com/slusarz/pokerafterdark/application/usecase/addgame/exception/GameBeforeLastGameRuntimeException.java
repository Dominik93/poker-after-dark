package com.slusarz.pokerafterdark.application.usecase.addgame.exception;

import com.slusarz.pokerafterdark.domain.game.Game;

public class GameBeforeLastGameRuntimeException extends RuntimeException {
    public GameBeforeLastGameRuntimeException(Game lastGame, Game gameToAdd) {
        super("Try add game [" + gameToAdd + "] before last game [" + lastGame + "]");
    }
}
