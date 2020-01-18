package com.slusarz.pokerafterdark.application.usecase.removegame.event;

import com.slusarz.pokerafterdark.application.events.Event;
import com.slusarz.pokerafterdark.domain.game.GameId;
import lombok.Value;

@Value(staticConstructor = "of")
public class RemoveGameEvent implements Event {

    private GameId gameId;

}
