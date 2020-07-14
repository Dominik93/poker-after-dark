package com.slusarz.pokerafterdark.application.players;

import com.slusarz.pokerafterdark.application.player.PlayerProjection;

import java.util.List;

public interface PlayersQueryRepository {
    List<PlayerProjection> readPlayers();

}
