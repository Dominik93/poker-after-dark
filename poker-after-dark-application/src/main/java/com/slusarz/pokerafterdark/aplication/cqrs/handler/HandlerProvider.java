package com.slusarz.pokerafterdark.aplication.cqrs.handler;

public interface HandlerProvider<RESULT, TYPE> {

    Handler<RESULT, TYPE> getHandler(TYPE query);

}