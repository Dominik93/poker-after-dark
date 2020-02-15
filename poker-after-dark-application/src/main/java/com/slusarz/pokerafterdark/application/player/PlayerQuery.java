package com.slusarz.pokerafterdark.application.player;

import com.slusarz.pokerafterdark.application.cqrs.query.Query;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import lombok.Value;

@Value(staticConstructor = "of")
public class PlayerQuery implements Query {

    private PlayerId playerId;

}
