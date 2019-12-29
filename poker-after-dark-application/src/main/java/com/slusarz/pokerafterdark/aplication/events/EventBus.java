package com.slusarz.pokerafterdark.aplication.events;


// TODO decorator pattern?
public interface EventBus {
    void fireEvent(Event event);
}
