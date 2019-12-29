package com.slusarz.pokerafterdark.aplication.usecase.removegame.event;

import com.slusarz.pokerafterdark.aplication.events.EventHandler;
import com.slusarz.pokerafterdark.aplication.livewinnings.LiveWinningsRepository;
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
