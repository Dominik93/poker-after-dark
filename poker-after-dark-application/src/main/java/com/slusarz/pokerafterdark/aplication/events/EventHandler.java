package com.slusarz.pokerafterdark.aplication.events;

public interface EventHandler<EVENT> {

    void handle(EVENT event);

    Class getEvent();

}
