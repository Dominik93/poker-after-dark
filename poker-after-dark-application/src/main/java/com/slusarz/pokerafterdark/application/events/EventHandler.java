package com.slusarz.pokerafterdark.application.events;

public interface EventHandler<EVENT> {

    void handle(EVENT event);

    Class getEvent();

}
