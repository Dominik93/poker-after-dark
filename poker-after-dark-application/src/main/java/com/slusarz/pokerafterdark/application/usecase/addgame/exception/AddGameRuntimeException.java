package com.slusarz.pokerafterdark.application.usecase.addgame.exception;

public class AddGameRuntimeException extends RuntimeException {
    public AddGameRuntimeException() {
        super("Cannot add game because winnings dont match.");
    }
}
