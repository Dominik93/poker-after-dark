package com.slusarz.pokerafterdark.application.common.events;

public interface EventHandler<EVENT> {

    void handle(EVENT event);

    Class getEvent();

}
