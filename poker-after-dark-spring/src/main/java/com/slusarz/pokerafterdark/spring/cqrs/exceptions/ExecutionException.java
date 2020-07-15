package com.slusarz.pokerafterdark.spring.cqrs.exceptions;

public class ExecutionException extends RuntimeException {
    public ExecutionException(Object command) {
        super("No handler for [" + command + "]. Consider registering handler.");
    }
}
