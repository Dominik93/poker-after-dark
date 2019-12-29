package com.slusarz.pokerafterdark.aplication.usecase.addgame.event;

import com.slusarz.pokerafterdark.aplication.events.Event;
import com.slusarz.pokerafterdark.domain.game.Game;
import lombok.Value;

@Value(staticConstructor = "of")
public class AddGameEvent implements Event {

    private Game game;

}
