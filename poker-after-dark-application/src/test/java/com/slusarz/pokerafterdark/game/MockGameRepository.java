package com.slusarz.pokerafterdark.game;

import com.slusarz.pokerafterdark.application.usecase.addgame.GameRepository;
import com.slusarz.pokerafterdark.domain.game.Game;
import com.slusarz.pokerafterdark.domain.game.GameId;
import com.slusarz.pokerafterdark.domain.game.Pot;
import com.slusarz.pokerafterdark.domain.participant.Earnings;
import com.slusarz.pokerafterdark.domain.participant.Participant;
import com.slusarz.pokerafterdark.domain.player.PlayerId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MockGameRepository implements GameRepository {

    private Map<GameId, Game> db = new HashMap<>();

    public MockGameRepository() {
        GameId gameId = GameId.of("GAME_1");
        List<Participant> participants = new ArrayList<>();
        participants.add(Participant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        participants.add(Participant.of(PlayerId.of("PLAYER_2"), Earnings.of(-20)));
        Game game = Game.of(gameId, PlayerId.of("PLAYER_1"), LocalDate.now(), Pot.of(20), participants);
        db.put(gameId, game);
    }

    @Override
    public GameId generateId() {
        return GameId.of(UUID.randomUUID().toString());
    }

    @Override
    public Game save(Game game) {
        return db.put(game.getGameId(), game);
    }

    @Override
    public void remove(GameId gameId) {
        db.remove(gameId);
    }
}
