package com.slusarz.pokerafterdark.spring.common.eventbus;

import com.slusarz.pokerafterdark.application.common.events.Event;
import com.slusarz.pokerafterdark.application.common.events.EventBus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@AllArgsConstructor
public class AfterCommitEventBus implements EventBus {

    private EventBus asyncEventBus;

    public void fireEvent(Event event) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            public void afterCommit() {
                asyncEventBus.fireEvent(event);
            }
        });
    }

}
