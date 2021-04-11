package com.slusarz.pokerafterdark.application.usecase.removegame.event;

import com.slusarz.pokerafterdark.application.common.events.EventHandler;
import com.slusarz.pokerafterdark.application.livewinnings.PlayerProjectionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class RemoveGameEventHandler implements EventHandler<RemoveGameEvent> {

    private PlayerProjectionRepository synchronize;

    @Override
    public void handle(RemoveGameEvent event) {
        synchronize.synchronize();
    }

    @Override
    public Class getEvent() {
        return RemoveGameEvent.class;
    }
}
