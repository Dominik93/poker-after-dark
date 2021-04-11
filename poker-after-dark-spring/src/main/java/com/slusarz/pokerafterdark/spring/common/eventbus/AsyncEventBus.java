package com.slusarz.pokerafterdark.spring.common.eventbus;

import com.slusarz.pokerafterdark.application.common.events.Event;
import com.slusarz.pokerafterdark.application.common.events.EventBus;
import com.slusarz.pokerafterdark.application.common.events.EventHandler;
import com.slusarz.pokerafterdark.application.common.events.EventHandlerExecutor;
import com.slusarz.pokerafterdark.application.common.events.EventHandlerProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

@Slf4j
@AllArgsConstructor
public class AsyncEventBus implements EventBus {

    private EventHandlerProvider eventsProvider;

    private EventHandlerExecutor eventHandlerExecutor;

    @Async
    public void fireEvent(Event event) {
        log.info("Fire event [" + event + "]");
        this.handleEvents(event);
    }

    private void handleEvents(Event event) {
        for (EventHandler eventHandler : eventsProvider.getEvents(event.getClass())) {
            eventHandlerExecutor.execute(eventHandler, event);
        }
    }

}
