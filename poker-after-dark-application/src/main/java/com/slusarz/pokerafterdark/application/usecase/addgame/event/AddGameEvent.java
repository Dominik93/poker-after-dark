package com.slusarz.pokerafterdark.application.usecase.addgame.event;

import com.slusarz.pokerafterdark.application.events.Event;
import com.slusarz.pokerafterdark.domain.game.Game;
import lombok.Value;

@Value(staticConstructor = "of")
public class AddGameEvent implements Event {

    private Game game;

}
