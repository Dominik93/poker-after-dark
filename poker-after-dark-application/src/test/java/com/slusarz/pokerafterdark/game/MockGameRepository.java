package com.slusarz.pokerafterdark.game;

import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.GameRepository;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MockGameRepository implements GameRepository {

    private Map<GameId, Game> db = new HashMap<>();

    public MockGameRepository() {
        GameId cashGameId = GameId.of("GAME_1");
        Game cashGame = Game.of(cashGameId, PlayerId.of("PLAYER_1"), LocalDate.now(), Pot.of(20));
        db.put(cashGame.getGameId(), cashGame);
    }

    @Override
    public void remove(GameId cashGameId) {
        db.remove(cashGameId);
    }

    @Override
    public Game readLast() {
        return db.values().stream().min(Comparator.comparing(Game::getOccurrenceDate)).orElse(null);
    }

}