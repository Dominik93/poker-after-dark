package com.slusarz.pokerafterdark.application.cqrs.handler;

public interface HandlerProvider<RESULT, TYPE> {

    Handler<RESULT, TYPE> getHandler(TYPE query);

}