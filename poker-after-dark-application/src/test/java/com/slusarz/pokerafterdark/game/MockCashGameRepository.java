package com.slusarz.pokerafterdark.game;

import com.slusarz.pokerafterdark.domain.cashgame.CashGame;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameId;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameParticipant;
import com.slusarz.pokerafterdark.domain.cashgame.CashGameRepository;
import com.slusarz.pokerafterdark.domain.earnings.Earnings;
import com.slusarz.pokerafterdark.domain.player.PlayerId;
import com.slusarz.pokerafterdark.domain.pot.Pot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MockCashGameRepository implements CashGameRepository {

    private Map<CashGameId, CashGame> db = new HashMap<>();

    public MockCashGameRepository() {
        CashGameId cashGameId = CashGameId.of("GAME_1");
        List<CashGameParticipant> cashGameParticipants = new ArrayList<>();
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_1"), Earnings.of(20)));
        cashGameParticipants.add(CashGameParticipant.of(PlayerId.of("PLAYER_2"), Earnings.of(-20)));
        CashGame cashGame = CashGame.of(cashGameId, PlayerId.of("PLAYER_1"), LocalDate.now(), Pot.of(20), cashGameParticipants);
        db.put(cashGameId, cashGame);
    }

    @Override
    public CashGameId generateId() {
        return CashGameId.of(UUID.randomUUID().toString());
    }

    @Override
    public void save(CashGame cashGame) {
        db.put(cashGame.getCashGameId(), cashGame);
    }

}
