package com.slusarz.pokerafterdark.application.cqrs.handler;

public interface Handler<RESULT, TYPE> {

    RESULT handle(TYPE query);
}
