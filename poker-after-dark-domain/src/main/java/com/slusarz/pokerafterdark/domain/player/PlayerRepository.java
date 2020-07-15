package com.slusarz.pokerafterdark.domain.player;

import java.util.List;
import java.util.Set;

public interface PlayerRepository {

    PlayerId generateId();

    void save(Player player);

    void save(List<Player> players);

    List<Player> readPlayers(Set<PlayerId> playerIds);

    boolean playerExist(PlayerName playerName);
}
