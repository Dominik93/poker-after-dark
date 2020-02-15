package com.slusarz.pokerafterdark.application.player;

import com.slusarz.pokerafterdark.domain.player.PlayerId;

import java.util.List;

public interface PlayerQueryRepository {
    List<PlayerProjection> readPlayers();

    PlayerProjection read(PlayerId playerId);

}
