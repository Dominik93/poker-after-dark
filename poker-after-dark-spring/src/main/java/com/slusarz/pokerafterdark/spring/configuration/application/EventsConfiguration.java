package com.slusarz.pokerafterdark.spring.configuration.application;

import com.slusarz.pokerafterdark.application.common.events.EventBus;
import com.slusarz.pokerafterdark.application.common.events.EventHandler;
import com.slusarz.pokerafterdark.application.common.events.EventHandlerExecutor;
import com.slusarz.pokerafterdark.application.common.events.EventHandlerProvider;
import com.slusarz.pokerafterdark.spring.common.eventbus.AfterCommitEventBus;
import com.slusarz.pokerafterdark.spring.common.eventbus.AsyncEventBus;
import com.slusarz.pokerafterdark.spring.common.eventhandler.TransactionalEventHandlerExecutor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class EventsConfiguration {

    @Bean
    public EventHandlerProvider eventHandlerProvider(List<EventHandler> eventHandlers) {
        Map<Class, List<EventHandler>> handlers = eventHandlers.stream().collect(Collectors.groupingBy(EventHandler::getEvent));
        return new EventHandlerProvider(handlers);
    }

    @Bean("asyncEventBus")
    public EventBus asyncEventBus(EventHandlerProvider eventHandlerProvider, EventHandlerExecutor eventHandlerExecutor) {
        return new AsyncEventBus(eventHandlerProvider, eventHandlerExecutor);
    }

    @Bean("afterCommitEventBus")
    public EventBus afterCommitEventBus(@Qualifier("asyncEventBus") EventBus eventBus) {
        return new AfterCommitEventBus(eventBus);
    }

    @Bean
    public EventHandlerExecutor eventHandlerExecutor(){
        return new TransactionalEventHandlerExecutor();
    }
}