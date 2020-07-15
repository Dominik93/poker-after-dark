package com.slusarz.pokerafterdark.player;

import com.slusarz.pokerafterdark.domain.player.Player;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.player.PlayerName;
import com.slusarz.pokerafterdark.domain.player.PlayerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class MockPlayerRepository implements PlayerRepository {

    private Map<PlayerId, Player> db = new HashMap<>();

    public MockPlayerRepository() {
        PlayerId playerId = PlayerId.of("1");
        db.put(playerId, Player.newPlayer(playerId, PlayerName.of("EXISTING_NAME")));
    }

    @Override
    public PlayerId generateId() {
        return PlayerId.of(UUID.randomUUID().toString());
    }

    @Override
    public void save(Player player) {
        db.put(player.getPlayerId(), player);
    }

    @Override
    public void save(List<Player> players) {
        players.forEach(this::save);
    }

    @Override
    public List<Player> readPlayers(Set<PlayerId> playerIds) {
        List<Player> players = new ArrayList<>();
        playerIds.forEach(playerId -> players.add(db.get(playerId)));
        return players;
    }

    @Override
    public boolean playerExist(PlayerName playerName) {
        return db.values().stream().anyMatch(player -> player.getPlayerName().equals(playerName));
    }
}
