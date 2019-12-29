package com.slusarz.pokerafterdark.aplication.usecase.removegame.event;

import com.slusarz.pokerafterdark.aplication.events.Event;
import com.slusarz.pokerafterdark.domain.game.GameId;
import lombok.Value;

@Value(staticConstructor = "of")
public class RemoveGameEvent implements Event {

    private GameId gameId;

}
