package com.slusarz.pokerafterdark.application.events;

public interface EventHandlerExecutor {

    void execute(EventHandler eventHandler, Event event);
}
