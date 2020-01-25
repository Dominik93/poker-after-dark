package com.slusarz.pokerafterdark.application.usecase.addgame.exception;

public class PotNotMatchRuntimeException extends RuntimeException {
    public PotNotMatchRuntimeException(double sum) {
        super("Cannot add game because winnings [" + sum + "] don`t equals 0.");
    }
}
