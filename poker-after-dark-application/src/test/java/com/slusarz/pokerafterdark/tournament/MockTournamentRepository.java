package com.slusarz.pokerafterdark.tournament;

import com.slusarz.pokerafterdark.domain.tournament.Tournament;
import com.slusarz.pokerafterdark.domain.tournament.TournamentId;
import com.slusarz.pokerafterdark.domain.tournament.TournamentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MockTournamentRepository implements TournamentRepository {

    private Map<TournamentId, Tournament> db = new HashMap<>();

    @Override
    public TournamentId generateId() {
        return TournamentId.of(UUID.randomUUID().toString());
    }

    @Override
    public void save(Tournament tournament) {
        db.put(tournament.getTournamentId(), tournament);
    }
}
