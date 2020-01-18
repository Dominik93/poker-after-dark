package com.slusarz.pokerafterdark.application.player;

import java.util.List;

public interface PlayerQueryRepository {
    List<PlayerProjection> readPlayers();

}
