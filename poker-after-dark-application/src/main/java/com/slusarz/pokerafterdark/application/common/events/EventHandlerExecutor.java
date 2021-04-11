package com.slusarz.pokerafterdark.application.common.events;

public interface EventHandlerExecutor {

    void execute(EventHandler eventHandler, Event event);
}
