package com.slusarz.pokerafterdark.application.common.events;


// TODO decorator pattern?
public interface EventBus {
    void fireEvent(Event event);
}
