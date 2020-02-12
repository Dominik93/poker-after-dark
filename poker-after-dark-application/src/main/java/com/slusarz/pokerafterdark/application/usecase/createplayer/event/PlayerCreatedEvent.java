package com.slusarz.pokerafterdark.application.usecase.createplayer.event;

import com.slusarz.pokerafterdark.application.events.Event;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.Value;

@Value(staticConstructor = "of")
public class PlayerCreatedEvent implements Event {

    private PlayerId playerId;

}
