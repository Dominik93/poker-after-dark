package com.slusarz.pokerafterdark.aplication.usecase.createplayer;

import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;

import java.util.List;
import java.util.Set;

public interface PlayerRepository {

    PlayerId generateId();
    void save(Player playerName);
    void save(List<Player> players);
    List<Player> readPlayers(Set<PlayerId> keySet);
}
