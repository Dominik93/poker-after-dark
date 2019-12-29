package com.slusarz.pokerafterdark.aplication.events;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class EventHandlerProvider {

    private Map<Class, List<EventHandler>> events;

    public List<EventHandler> getEvents(Class event) {
        return events.getOrDefault(event, new ArrayList<>());
    }
}
