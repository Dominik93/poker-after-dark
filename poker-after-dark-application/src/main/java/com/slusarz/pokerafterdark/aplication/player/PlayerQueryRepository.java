package com.slusarz.pokerafterdark.aplication.player;

import java.util.List;

public interface PlayerQueryRepository {
    List<PlayerProjection> readPlayers();

}
