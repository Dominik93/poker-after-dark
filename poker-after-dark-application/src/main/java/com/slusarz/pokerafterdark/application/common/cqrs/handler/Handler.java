package com.slusarz.pokerafterdark.application.common.cqrs.handler;

public interface Handler<RESULT, TYPE> {

    RESULT handle(TYPE query);
}
