package com.slusarz.pokerafterdark.spring.common.eventhandler;

import com.slusarz.pokerafterdark.application.common.events.Event;
import com.slusarz.pokerafterdark.application.common.events.EventHandler;
import com.slusarz.pokerafterdark.application.common.events.EventHandlerExecutor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@AllArgsConstructor
public class TransactionalEventHandlerExecutor implements EventHandlerExecutor {

    @Transactional
    public void execute(EventHandler eventHandler, Event event) {
        log.info("Handle event by [" + eventHandler.getClass().toGenericString() + "]");
        eventHandler.handle(event);
        log.info("Event [" + event + "] handled by [" + eventHandler.getClass().toGenericString() + "]");
    }
}
