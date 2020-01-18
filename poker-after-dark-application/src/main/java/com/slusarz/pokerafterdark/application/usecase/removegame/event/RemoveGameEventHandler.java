package com.slusarz.pokerafterdark.application.usecase.removegame.event;

import com.slusarz.pokerafterdark.application.events.EventHandler;
import com.slusarz.pokerafterdark.application.livewinnings.LiveWinningsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class RemoveGameEventHandler implements EventHandler<RemoveGameEvent> {

    private LiveWinningsRepository liveWinningsJpaRepository;

    @Override
    public void handle(RemoveGameEvent event) {
        liveWinningsJpaRepository.synchronize();
    }

    @Override
    public Class getEvent() {
        return RemoveGameEvent.class;
    }
}
