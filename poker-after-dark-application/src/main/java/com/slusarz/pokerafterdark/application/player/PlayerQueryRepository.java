package com.slusarz.pokerafterdark.application.player;

import com.slusarz.pokerafterdark.domain.player.PlayerId;

public interface PlayerQueryRepository {

    PlayerProjection read(PlayerId playerId);

}
