package com.slusarz.pokerafterdark.aplication.cqrs.handler;

public interface Handler<RESULT, TYPE> {

    RESULT handle(TYPE query);
}
