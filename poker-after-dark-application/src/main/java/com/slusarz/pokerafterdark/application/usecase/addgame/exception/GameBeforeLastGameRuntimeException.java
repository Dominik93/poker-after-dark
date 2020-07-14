package com.slusarz.pokerafterdark.application.usecase.addgame.exception;

import com.slusarz.pokerafterdark.domain.game.Game;

import java.time.LocalDate;

public class GameBeforeLastGameRuntimeException extends RuntimeException {
    public GameBeforeLastGameRuntimeException(Game lastCashGame, LocalDate date) {
        super("Try add game with date [" + date + "] witch is before last game [" + lastCashGame + "]");
    }
}
