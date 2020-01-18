package com.slusarz.pokerafterdark.application.events;


// TODO decorator pattern?
public interface EventBus {
    void fireEvent(Event event);
}
